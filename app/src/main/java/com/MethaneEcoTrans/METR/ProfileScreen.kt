package com.MethaneEcoTrans.METR

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.alpha
import androidx.compose.foundation.clickable
import androidx.navigation.NavController
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import com.MethaneEcoTrans.METR.theme.CustomTurquoiseBlue
import com.MethaneEcoTrans.METR.theme.CustomTrafficWhite
import com.MethaneEcoTrans.METR.theme.CustomCarpiBlue
import com.MethaneEcoTrans.METR.theme.CustomDeepOrange
import com.MethaneEcoTrans.METR.theme.CustomEnterBarColor
import com.MethaneEcoTrans.METR.theme.CustomGrey
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.text.ifEmpty

@Composable
fun ProfileScreen(navController: NavController){
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomTrafficWhite)
    ){
        // Получаем размеры всего доступного экрана
        val boxWidth = this.maxWidth
        val boxHeight = this.maxHeight

        //взаимодействие с realtime database
        val database = FirebaseDatabase.getInstance("https://met-project-fdcef-default-rtdb.europe-west1.firebasedatabase.app/")
        val ref = database.getReference("users")

        // штуки для уведомлений
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()

        // Состояние для хранения последних заправок
        val latestRefills = remember { mutableStateOf<List<Triple<String, Double, Double>>>(emptyList()) }

        // экземпляры firebase
        val auth = Firebase.auth
        val user = auth.currentUser
        val userUid: String? = user?.uid
        Log.d("userUID", "Current UID: ${user?.uid ?: "null"}")
        val historyRef: DatabaseReference = database.reference.child("history")

        // данные
        var userHistory by remember {
            mutableStateOf<Map<String, Map<String, Map<String, Double>>>>(emptyMap())
        }

        // переменные ранспорта
        var currentVehicle by remember { mutableStateOf("") }
        var userVehicles by remember { mutableStateOf<List<String>>(emptyList()) }
        val vehiclesRef = database.getReference("users").child(user?.uid.toString()).child("vehicles")

        LaunchedEffect(Unit) {
            vehiclesRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val vehicles = userVehicles.toMutableList() // Сохраняем текущие значения
                    snapshot.children.forEach { child ->
                        child.getValue(String::class.java)?.let { newVehicle ->
                            if (!vehicles.contains(newVehicle)) { // Проверяем, нет ли уже такого авто
                                vehicles.add(newVehicle)
                            }
                        }
                    }
                    userVehicles = vehicles
                }

                override fun onCancelled(error: DatabaseError) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Ошибка загрузки данных: ${error.message}",
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            })
        }

        LaunchedEffect(userUid) {
            userUid?.let { uid ->
                historyRef.child(uid).addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val historyMap = mutableMapOf<String, Map<String, Map<String, Double>>>()

                        snapshot.children.forEach { vehicleSnapshot ->
                            val vehicleName = vehicleSnapshot.key ?: return@forEach
                            val datesMap = mutableMapOf<String, Map<String, Double>>()

                            vehicleSnapshot.children.forEach { dateSnapshot ->
                                val date = dateSnapshot.key ?: return@forEach
                                val volume = dateSnapshot.child("volume").getValue(Double::class.java) ?: 0.0
                                val sum = dateSnapshot.child("sum").getValue(Double::class.java) ?: 0.0

                                datesMap[date] = mapOf(
                                    "volume" to volume,
                                    "sum" to sum
                                )
                            }

                            historyMap[vehicleName] = datesMap
                        }

                        userHistory = historyMap
                    }

                    override fun onCancelled(error: DatabaseError) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Ошибка загрузки истории: ${error.message}",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                })
            }
        }

        // Получаем данные из userHistory
        LaunchedEffect(userHistory, currentVehicle) {
            val tempList = mutableListOf<Triple<String, Double, Double>>()

            // Фильтруем записи только для выбранного автомобиля
            userHistory[currentVehicle]?.forEach { (date, data) ->
                val volume = data["volume"] ?: 0.0
                val sum = data["sum"] ?: 0.0
                tempList.add(Triple(date, volume, sum))
            }

            // Сортируем по дате (новые сначала) и берем 6 последние записи
            latestRefills.value = tempList
                .sortedByDescending { it.first }
                .take(6)
        }
        

        // поле с предварительной историей
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = boxHeight/19 * 10 - 12.dp,
                    bottom = boxHeight/19 * 2,
                    start = boxWidth / 10,
                    end = boxWidth / 10 + 12.dp
                )
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = CustomTurquoiseBlue, shape = RoundedCornerShape(15.dp))
            ){
                // поле "История"
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 12.dp,
                            start = boxWidth / 10,
                            bottom = boxHeight / 18 * 6 - 12.dp,
                            end = boxWidth / 10 * 4 - 6.dp
                        )
                ){
                    Box(
                        modifier = Modifier
                            .size(boxWidth / 10 * 3, boxHeight / 18)
                            .border(1.dp, CustomTrafficWhite, shape = RoundedCornerShape(15.dp))
                    ){
                        Text(
                            text = "История",
                            modifier = Modifier
                                .align(Alignment.Center),
                            color = CustomTrafficWhite,
                            fontFamily = segoe_ui,
                            fontSize = 16.sp
                        )
                    }
                }
                // "поле выбор авто"
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = 12.dp,
                            start = boxWidth / 10 * 4 + 6.dp,
                            bottom = boxHeight / 18 * 6 - 12.dp,
                            end = boxWidth / 10
                        )
                ){
                    var expanded by remember { mutableStateOf(false) }
                    val focusManager = LocalFocusManager.current

                    Box(
                        modifier = Modifier
                            .size(boxWidth / 10 * 4, boxHeight / 18 * 1)
                            .background(color = CustomTurquoiseBlue, shape = RoundedCornerShape(15.dp))
                            .border(1.dp, CustomTrafficWhite, RoundedCornerShape(15.dp))
                            .clickable {
                                focusManager.clearFocus()
                                expanded = true
                            }
                    ) {
                        Text(
                            text = currentVehicle.ifEmpty { "Номер авто ≡" },
                            modifier = Modifier.align(Alignment.Center),
                            color = CustomTrafficWhite,
                            fontFamily = segoe_ui,
                            fontSize = 16.sp
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .width(boxWidth / 10 * 4)
                                .background(CustomTurquoiseBlue)
                        ) {
                            if (userVehicles.isEmpty()) {
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            "Добавьте автомобиль",
                                            color = CustomTrafficWhite
                                        )
                                    },
                                    onClick = { expanded = false }
                                )
                            } else {
                                userVehicles.forEach { vehicle ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                vehicle,
                                                color = CustomTrafficWhite
                                            )
                                        },
                                        onClick = {
                                            currentVehicle = vehicle
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                // поле с самой историей
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = boxHeight / 18,
                            start = boxWidth / 10 ,
                            bottom = boxHeight / 18,
                            end = boxWidth / 10
                        )
                ){
                    Box(
                        modifier = Modifier
                            .size(boxWidth / 10 * 6, boxHeight / 18 * 5)
                            .background(color = CustomTrafficWhite, shape = RoundedCornerShape(15.dp))
                    ){
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    top = 12.dp,
                                    start = 12.dp,
                                    end = 12.dp,
                                    bottom = 12.dp
                                )
                        ){
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(12.dp)
                                    .verticalScroll(rememberScrollState())
                            ) {
                                if (currentVehicle.isBlank()) {
                                    Text(
                                        text = "Выберите автомобиль",
                                        color = CustomGrey,
                                        fontFamily = segoe_ui,
                                        fontSize = 16.sp,
                                        modifier = Modifier.fillMaxSize(),
                                        textAlign = TextAlign.Center
                                    )
                                } else {
                                    // Отображаем последние 6 заправок
                                    latestRefills.value.forEach { (date, volume, sum) ->
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(vertical = 6.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = date.replace(";", "."),
                                                color = CustomGrey,
                                                fontFamily = segoe_ui,
                                                fontSize = 12.sp
                                            )
                                            Text(
                                                text = "%.1f л".format(volume),
                                                color = CustomGrey,
                                                fontFamily = segoe_ui,
                                                fontSize = 12.sp
                                            )
                                            Text(
                                                text = "%.2f ₽".format(sum),
                                                color = CustomGrey,
                                                fontFamily = segoe_ui,
                                                fontSize = 12.sp
                                            )
                                        }
                                        Divider(color = CustomGrey.copy(alpha = 0.3f), thickness = 1.dp)
                                    }
                                }

                            }

                        }
                    }
                }
                // поле загрузить еще
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = boxHeight / 18 * 6 - 12.dp,
                            start = boxWidth / 10 ,
                            bottom = 12.dp,
                            end = boxWidth / 10
                        )
                ){
                    Text(
                        text = "Загрузить еще",
                        modifier = Modifier
                            .clickable{navController.navigate("HistoryScreen")}
                            .align(Alignment.Center),
                        color = CustomTrafficWhite,
                        fontFamily = segoe_ui,
                        fontSize = 12.sp
                    )
                }

            }
        }
        // Поле с навигацией
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = boxHeight / 18 * 16,
                    bottom = boxHeight / 18 * 1 - 12.dp
                )
        ){
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = boxWidth / 10,
                        vertical = 12.dp
                    )
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(15.dp),
                    border = BorderStroke(1.dp, CustomDeepOrange),
                    color = CustomTrafficWhite
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp), // Отступы по бокам
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        listOf(
                            R.drawable.vector_telegram to "contacts",
                            R.drawable.vector_history to "history",
                            R.drawable.vector_home to "main"
                        ).forEach { (iconRes, description) ->
                            Box(
                                modifier = Modifier
                                    .size(24.dp), // Увеличиваем общий размер
                                contentAlignment = Alignment.Center
                            ){
                                Image(
                                    painter = painterResource(id = iconRes),
                                    contentDescription = "${description}Icon",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable{
                                            if (description == "main"){
                                                navController.navigate("MainScreen")
                                            }
                                            if (description == "history"){
                                                navController.navigate("HistoryScreen")
                                            }

                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewProfileScreen() {
    val navController = rememberNavController()
    ProfileScreen(navController)
}