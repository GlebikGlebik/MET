package com.MethaneEcoTrans.METR

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.foundation.border
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.MethaneEcoTrans.METR.theme.CustomTurquoiseBlue
import com.MethaneEcoTrans.METR.theme.CustomTrafficWhite
import com.MethaneEcoTrans.METR.theme.CustomCarpiBlue
import com.MethaneEcoTrans.METR.theme.CustomDeepOrange
import com.MethaneEcoTrans.METR.theme.CustomEnterBarColor
import com.MethaneEcoTrans.METR.theme.CustomGrey

@Composable
fun MainScreen(navController: NavController){
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(color = CustomTrafficWhite)
    ){
        // Получаем размеры всего доступного экрана
        val boxWidth = this.maxWidth
        val boxHeight = this.maxHeight


        //кнопка добавить заправку
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = boxWidth / 10,
                    end = boxWidth / 10,
                    top = boxHeight / 18 * 9 - 12.dp,
                    bottom = boxHeight / 18 * 7
                )
        ) {
            Box(
                modifier = Modifier
                    .size(boxWidth / 10 * 4, boxHeight / 18 * 1)
                    .background(color = CustomCarpiBlue, shape =  RoundedCornerShape(15.dp))

            ){
                Text(
                    text = "Добавить заправку",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { navController.navigate("EnterScreen") },
                    color = CustomTrafficWhite,
                    fontFamily = segoe_ui,
                    fontSize = 16.sp
                )
            }
        }

        // поле с акциями
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = boxWidth / 10,
                    end = boxWidth / 10,
                    top = boxHeight/ 18 * 10,
                    bottom = boxHeight / 18 + 12.dp
                )
        ){
            Box(
                modifier = Modifier
                    .size(boxWidth / 10 * 8, boxHeight / 18 * 6 )
                    .border(1.dp, CustomDeepOrange, shape = RoundedCornerShape(15.dp))
                    .background(color = CustomTrafficWhite, shape = RoundedCornerShape(15.dp))
            ){

            }
        }

        // поле с навигацией
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = boxWidth / 10,
                    end = boxWidth / 10,
                    top = boxHeight/ 18 * 16 + 12.dp,
                    bottom = boxHeight/ 18
                )
        ){
            Box(
                modifier = Modifier
                    .size(boxWidth / 10 * 8, boxHeight / 18 * 1 )
                    .border(1.dp, CustomDeepOrange, shape = RoundedCornerShape(15.dp))
                    .background(color = CustomTrafficWhite, shape = RoundedCornerShape(15.dp))
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.history_icon_1),
                        contentDescription = "socialNetworkIcon",
                        modifier = Modifier
                            .requiredSize(boxHeight / 11),
                        contentScale = ContentScale.Fit
                    )
                    Image(
                        painter = painterResource(id = R.drawable.history_icon_1),
                        contentDescription = "historyIcon",
                        modifier = Modifier
                            .requiredSize(boxHeight / 11),
                        contentScale = ContentScale.Fit
                    )
                    Image(
                        painter = painterResource(id = R.drawable.history_icon_1),
                        contentDescription = "statisticIcon",
                        modifier = Modifier
                            .requiredSize(boxHeight / 11),
                        contentScale = ContentScale.Fit
                    )
                    Image(
                        painter = painterResource(id = R.drawable.history_icon_1),
                        contentDescription = "profileIcon",
                        modifier = Modifier
                            .requiredSize(boxHeight / 11),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }


    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMainScreen() {
    val navController = rememberNavController()
    MainScreen(navController)
}