package com.MethaneEcoTrans.METR


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.draw.alpha
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import android.util.Patterns
import androidx.compose.foundation.layout.offset
import com.MethaneEcoTrans.METR.theme.CustomTurquoiseBlue
import com.MethaneEcoTrans.METR.theme.CustomTrafficWhite
import com.MethaneEcoTrans.METR.theme.CustomCarpiBlue
import com.MethaneEcoTrans.METR.theme.CustomEnterBarColor
import com.MethaneEcoTrans.METR.theme.CustomGrey
import com.MethaneEcoTrans.METR.theme.CustomErrorBarBackgroundColor


val segoe_ui_bold = FontFamily(
    Font(R.font.segoe_ui_bold)
)

val segoe_ui = FontFamily(
    Font(R.font.segoe_ui)
)

@Composable
fun RegistrationScreen(navController: NavController) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTurquoiseBlue)
    ) {

        // Получаем размеры внутреннего экрана, которые равняются половине экрана
        val boxWidth = this.maxWidth * 0.5f
        val boxHeight = this.maxHeight * 0.5f
        var k by remember {mutableStateOf(0.dp)}

        // Отслеживание состояний
        var password by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var surname by remember { mutableStateOf("") }

        // Состояния для ошибок
        var emailError by remember { mutableStateOf(false) }
        var emptyFieldsError by remember { mutableStateOf(false) }

        // Состояния для фокуса
        var isFocusedName by remember { mutableStateOf(false) }
        var isFocusedSurname by remember { mutableStateOf(false) }
        var isFocusedEmail by remember { mutableStateOf(false) }
        var isFocusedPassword by remember { mutableStateOf(false) }

        // обавляем функцию, проверяющую корректность email
        fun isEmailValid(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        // Обработчик нажатия на кнопку регистрации
        fun onRegisterClick() {
            emailError = !isEmailValid(email)
            emptyFieldsError = name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()

            if (!emailError && !emptyFieldsError) {
                navController.navigate("NextScreen")
            }
        }

        // Уведомление некорректный email
        if (emailError && !emptyFieldsError) {
            Box(
                modifier = Modifier
                    .requiredSize(boxWidth, boxHeight / 11 + 5.dp)
                    .offset(x = boxWidth / 2, y = 170.dp)
                    .background(color = CustomErrorBarBackgroundColor, shape = RoundedCornerShape(15.dp))
            ){
                Text(
                    text = "Неправильный формат email",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            start = 5.dp,
                            end = 5.dp
                        ),
                    fontFamily = segoe_ui,
                    color = CustomTrafficWhite,
                    fontSize = 12.sp
                )
            }
        }

        // уведомление некорректный email и неполный ввод
        if (emailError && emptyFieldsError) {
            Box(
                modifier = Modifier
                    .requiredSize(boxWidth + 20.dp, (boxHeight / 11) * 2 + 5.dp)
                    .offset(x = boxWidth / 2 - 10.dp, y = 128.dp)
                    .background(color = CustomErrorBarBackgroundColor, shape = RoundedCornerShape(15.dp))
            ){
                Text(
                    text = "Неправильный формат email",
                    modifier = Modifier
                        .padding(
                            bottom = boxHeight / 11,
                            start = 5.dp,
                            end = 5.dp
                        )
                        .align(Alignment.Center),
                    fontFamily = segoe_ui,
                    color = CustomTrafficWhite,
                    fontSize = 12.sp
                )

                Text(
                    text = "Все поля должны быть заполнены",
                    modifier = Modifier
                        .padding(
                            top = boxHeight / 11,
                            start = 5.dp,
                            end = 5.dp
                        )
                        .align(Alignment.Center),
                    fontFamily = segoe_ui,
                    color = CustomTrafficWhite,
                    fontSize = 12.sp
                )
            }
        }

        // уведомление неполный ввод
        if (emptyFieldsError && !emailError) {
            Box(
                modifier = Modifier
                    .requiredSize(boxWidth + 20.dp, boxHeight / 11 + 5.dp)
                    .offset(x = boxWidth / 2 - 10.dp, y = 170.dp)
                    .background(color = CustomErrorBarBackgroundColor, shape = RoundedCornerShape(15.dp))
            ){
                Text(
                    text = "Все поля должны быть заполнены",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            start = 5.dp,
                            end = 5.dp
                        ),
                    fontFamily = segoe_ui,
                    color = CustomTrafficWhite,
                    fontSize = 12.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .requiredSize(boxWidth, boxHeight)
                .align(Alignment.Center)
                .background(CustomTrafficWhite, shape = RoundedCornerShape(15.dp))
        ) {
            Text(
                text = "Регистрация",
                modifier = Modifier
                    .padding(top = boxHeight / 11 * 1)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontFamily = segoe_ui_bold,
                color = CustomCarpiBlue,
                fontSize = 24.sp
            )
            // поле для ввода фамилии
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = boxHeight / 11 * 3 + 5.dp,
                        start = boxWidth / 11,
                        end = boxWidth / 11,
                        bottom = boxHeight / 11 * 7 + 5.dp

                    )
                    .background(CustomEnterBarColor, shape = RoundedCornerShape(10.dp))
            ) {
                //плейсхолдер
                if (surname.isEmpty() && !isFocusedSurname) {
                    Text(
                        text = "фамилия",
                        modifier = Modifier
                            .alpha(0.5f)
                            .align(Alignment.Center)
                            .padding(
                                start = 5.dp,
                                end = 104.dp
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
                        .onFocusChanged { focusState -> isFocusedSurname = focusState.isFocused },
                    value = surname,
                    onValueChange = { newText ->
                        // Ограничиваем длину пароля до 14 символов
                        if (newText.length <= 24) {
                            surname = newText
                        }
                    },
                    textStyle = TextStyle(
                        color = CustomGrey,
                        fontSize = 12.sp
                    ),
                    cursorBrush = Brush.verticalGradient(
                        colors = listOf(
                            CustomGrey.copy(alpha = 0.5f),
                            CustomGrey.copy(alpha = 0.5f)
                        ),
                        startY = 0f,
                        endY = 12f
                    )
                )
            }
            //  поле для ввода имени
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
                if (name.isEmpty() && !isFocusedName) {
                    Text(
                        text = "имя",
                        modifier = Modifier
                            .alpha(0.5f)
                            .align(Alignment.Center)
                            .padding(
                                start = 5.dp,
                                end = 135.dp
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
                        .onFocusChanged { focusState -> isFocusedName = focusState.isFocused },
                    value = name,
                    onValueChange = { newText ->
                        // Ограничиваем длину пароля до 14 символов
                        if (newText.length <= 24) {
                            name = newText
                        }
                    },
                    textStyle = TextStyle(
                        color = CustomGrey,
                        fontSize = 12.sp
                    ),
                    cursorBrush = Brush.verticalGradient(
                        colors = listOf(
                            CustomGrey.copy(alpha = 0.5f),
                            CustomGrey.copy(alpha = 0.5f)
                        ),
                        startY = 0f,
                        endY = 12f
                    )
                )
            }
            //  поле для ввода e-mail
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = boxHeight / 11 * 5 + 5.dp,
                        start = boxWidth / 11,
                        end = boxWidth / 11,
                        bottom = boxHeight / 11 * 5 + 5.dp

                    )
                    .background(CustomEnterBarColor, shape = RoundedCornerShape(10.dp))
            ) {
                //плейсхолдер
                if (email.isEmpty() && !isFocusedEmail) {
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
                        colors = listOf(
                            CustomGrey.copy(alpha = 0.5f),
                            CustomGrey.copy(alpha = 0.5f)
                        ),
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
                        top = boxHeight / 11 * 6 + 5.dp,
                        start = boxWidth / 11,
                        end = boxWidth / 11,
                        bottom = boxHeight / 11 * 4 + 5.dp
                    )
                    .background(CustomEnterBarColor, shape = RoundedCornerShape(10.dp)),
            ) {
                // плейсхолдер
                if (password.isEmpty() && !isFocusedPassword) {
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
                        colors = listOf(
                            CustomGrey.copy(alpha = 0.5f),
                            CustomGrey.copy(alpha = 0.5f)
                        ),
                        startY = 0f,
                        endY = 12f
                    )
                )
            }
            //Кнопка регистрации
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
                    .clickable { onRegisterClick() }
            ) {
                Text(
                    text = "Регистрация",
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = CustomTrafficWhite,
                    fontFamily = segoe_ui,
                    fontSize = 16.sp
                )

            }
            //Кнопка Вход
            Box(
                modifier = Modifier
                    .requiredSize(boxWidth, boxHeight)
                    .padding(
                        top = boxHeight / 11 * 9 + 5.dp,
                        start = boxWidth / 11,
                        end = boxWidth / 11,
                        bottom = boxHeight / 11 * 1 + 5.dp

                    )
                    .background(CustomCarpiBlue, shape = RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = "Вход",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable { navController.navigate("EnterScreen") },
                    color = CustomTrafficWhite,
                    fontFamily = segoe_ui,
                    fontSize = 16.sp
                )

            }
        }
    }
}