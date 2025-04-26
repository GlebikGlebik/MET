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
import com.MethaneEcoTrans.METR.theme.CustomTurquoiseBlue
import com.MethaneEcoTrans.METR.theme.CustomTrafficWhite
import com.MethaneEcoTrans.METR.theme.CustomCarpiBlue
import com.MethaneEcoTrans.METR.theme.CustomEnterBarColor
import com.MethaneEcoTrans.METR.theme.CustomGrey

val segoe_ui_bold_enter_screen = FontFamily(
    Font(R.font.segoe_ui_bold)
)

val segoe_ui_enter_screen = FontFamily(
    Font(R.font.segoe_ui)
)

@Composable
fun EnterScreen(navController: NavController) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTurquoiseBlue)
    ) {

        // Получаем размеры внутреннего экрана, которые равняются половине экрана
        val boxWidth = this.maxWidth * 0.5f
        val boxHeight = this.maxHeight * 0.5f

        //отслеживание состояний
        var password by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var isFocusedEmail by remember { mutableStateOf(false) }
        var isFocusedPassword by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .requiredSize(boxWidth, boxHeight)
                .align(Alignment.Center)
                .background(CustomTrafficWhite, shape = RoundedCornerShape(15.dp))
        ) {
            Text(
                text = "Вход",
                modifier = Modifier
                    .padding(top = boxHeight / 11 * 2)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = segoe_ui_bold_enter_screen,
                color = CustomCarpiBlue,
                fontSize = 24.sp
            )
            //  поле для ввода e-mail
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = boxHeight / 11 * 4 + 5.dp,
                        start = boxWidth / 11,
                        end = boxWidth / 11,
                        bottom = boxHeight / 11 * 6 + 5.dp

                    )
                    .background(CustomEnterBarColor, shape = RoundedCornerShape(10.dp))
            ) {
                //плейсхолдер
                if(email.isEmpty() && !isFocusedEmail){
                    Text(
                        text = "e-mail",
                        modifier = Modifier
                            .alpha(0.5f)
                            .align(Alignment.Center)
                            .padding(
                                start = 5.dp,
                                end = 121.dp
                            ),
                        color = CustomGrey,
                        fontFamily = segoe_ui_enter_screen,
                        fontSize = 12.sp,
                    )
                }
                BasicTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 5.dp,
                            top = 10.dp,
                            end = 5.dp
                        )
                        .align(Alignment.Center)
                        .onFocusChanged { focusState -> isFocusedEmail = focusState.isFocused },
                    value = email,
                    onValueChange = { newText ->
                        // Ограничиваем длину пароля до 14 символов
                        if (newText.length <= 24) {
                            email = newText
                        }
                    },
                    textStyle = TextStyle(
                        color = CustomGrey,
                        fontSize = 12.sp
                    ),
                    cursorBrush = Brush.verticalGradient(
                        colors = listOf(CustomGrey.copy(alpha = 0.5f), CustomGrey.copy(alpha = 0.5f)),
                        startY = 0f,
                        endY = 12f
                    )
                )
            }
            // поле дял ввода пароля
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = boxHeight / 11 * 5 + 5.dp,
                        start = boxWidth / 11,
                        end = boxWidth / 11,
                        bottom = boxHeight / 11 * 5 + 5.dp
                    )
                    .background(CustomEnterBarColor, shape = RoundedCornerShape(10.dp)),
            ){
                // плейсхолдер
                if (password.isEmpty() && !isFocusedPassword){
                    Text(
                        text = "пароль",
                        modifier = Modifier
                            .alpha(0.5f)
                            .align(Alignment.Center)
                            .padding(
                                start = 5.dp,
                                end = 115.dp
                            ),
                        color = CustomGrey,
                        fontFamily = segoe_ui_enter_screen,
                        fontSize = 12.sp,
                    )

                }
                // сам ввод пароля
                BasicTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 5.dp,
                            top = 10.dp,
                            end = 5.dp
                        )
                        .align(Alignment.Center)
                        .onFocusChanged { focusState -> isFocusedPassword = focusState.isFocused },
                    value = password,
                    onValueChange = { newText ->
                        // Ограничиваем длину пароля до 14 символов
                        if (newText.length <= 24) {
                            password = newText
                        }
                    },
                    textStyle = TextStyle(
                        color = CustomGrey,
                        fontSize = 12.sp
                    ),
                    cursorBrush = Brush.verticalGradient(
                        colors = listOf(CustomGrey.copy(alpha = 0.5f), CustomGrey.copy(alpha = 0.5f)),
                        startY = 0f,
                        endY = 12f
                    )
                )
            }

            //Кнопка вход
            Box(
                modifier = Modifier
                    .requiredSize(boxWidth, boxHeight)
                    .padding(
                        top = boxHeight / 11 * 7 + 5.dp,
                        start = boxWidth / 11,
                        end = boxWidth / 11,
                        bottom = boxHeight / 11 * 3 + 5.dp

                    )
                    .background(CustomCarpiBlue, shape = RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = "Вход",
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = CustomTrafficWhite,
                    fontFamily = segoe_ui_enter_screen,
                    fontSize = 18.sp
                )

            }
            //Кнопка регистрация
            Box(
                modifier = Modifier
                    .requiredSize(boxWidth, boxHeight)
                    .padding(
                        top = boxHeight / 11 * 8 + 5.dp,
                        start = boxWidth / 11,
                        end = boxWidth / 11,
                        bottom = boxHeight / 11 * 2 + 5.dp

                    )
                    .background(CustomCarpiBlue, shape = RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = "Регистрация",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { navController.navigate("RegistrationScreen") },
                    color = CustomTrafficWhite,
                    fontFamily = segoe_ui_enter_screen,
                    fontSize = 18.sp
                )

            }
        }
    }
}