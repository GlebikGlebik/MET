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
import androidx.compose.ui.res.painterResource
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
                Image(
                    painter = painterResource(id = R.drawable.history_icon_1),
                    contentDescription = "HistoryIcon",
                    modifier = Modifier
                        .fillMaxSize()
                )
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