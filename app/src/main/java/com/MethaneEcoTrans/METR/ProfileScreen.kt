package com.MethaneEcoTrans.METR

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
import androidx.compose.material3.Surface
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.MethaneEcoTrans.METR.theme.CustomTurquoiseBlue
import com.MethaneEcoTrans.METR.theme.CustomTrafficWhite
import com.MethaneEcoTrans.METR.theme.CustomCarpiBlue
import com.MethaneEcoTrans.METR.theme.CustomDeepOrange
import com.MethaneEcoTrans.METR.theme.CustomEnterBarColor
import com.MethaneEcoTrans.METR.theme.CustomGrey
import com.google.firebase.database.FirebaseDatabase

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

        // поле с предварительной историей
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = boxHeight/19 * 10,
                    bottom = boxHeight/19 * 2,
                    start = boxWidth / 10,
                    end = boxWidth / 10
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
                    Box(
                        modifier = Modifier
                            .size(boxWidth / 10 * 3, boxHeight / 18)
                            .border(1.dp, CustomTrafficWhite, shape = RoundedCornerShape(15.dp))
                    ){
                        Text(
                            text = "Номер авто ≡",
                            modifier = Modifier
                                .align(Alignment.Center),
                            color = CustomTrafficWhite,
                            fontFamily = segoe_ui,
                            fontSize = 16.sp
                        )
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
                                    bottom = boxHeight / 18 * 4
                                )
                        ){
                            Row(
                                modifier = Modifier
                                    .background(color = CustomCarpiBlue)
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Text(
                                    text = "$"
                                )
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
                            R.drawable.vector_profile to "profile"
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
                                            if (description == "profile"){
                                                navController.navigate("ProfileScreen")
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