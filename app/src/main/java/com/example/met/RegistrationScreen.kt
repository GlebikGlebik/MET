package com.example.met

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.met.ui.theme.METTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.draw.alpha
import com.example.met.ui.theme.CustomTurquoiseBlue
import com.example.met.ui.theme.CustomTrafficWhite
import com.example.met.ui.theme.CustomCarpiBlue
import com.example.met.ui.theme.CustomEnterBarColor
import com.example.met.ui.theme.CustomGrey

class RegistrationScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            METTheme {
                // Определяем метод BackgroundEnterScreen
                BackgroundRegistrationScreen()
            }
        }
    }
}

val segoe_ui_bold = FontFamily(
    Font(R.font.segoe_ui_bold)
)

val segoe_ui = FontFamily(
    Font(R.font.segoe_ui)
)

@Composable
fun BackgroundRegistrationScreen() {
    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .background(CustomTurquoiseBlue)) {

        // Получаем размеры внутреннего экрана, которые равняются половине экрана
        val boxWidth = this.maxWidth * 0.5f
        val boxHeight = this.maxHeight * 0.5f

        Box(
            modifier = Modifier
                .requiredSize(boxWidth, boxHeight)
                .align(Alignment.Center)
                // Устанавливаем цвет и скругление
                .background(CustomTrafficWhite, shape = RoundedCornerShape(15.dp))
        ){
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
            //  поле для ввода e-mail
            Box(
                modifier = Modifier
                    .requiredSize(boxWidth, boxHeight)
                    .padding(
                        top = boxHeight / 11 * 3 + 5.dp,
                        start = boxWidth / 11,
                        end = boxWidth / 11,
                        bottom = boxHeight / 11 * 7 + 5.dp

                    )
                    .background(CustomEnterBarColor, shape = RoundedCornerShape(10.dp))
            ){
                Text(
                    text = "укажите e-mail",
                    modifier = Modifier
                        .alpha(0.5f)
                        .padding(
                            start = 5.dp,
                            top = 5.dp
                        ),
                    color = CustomGrey,
                    fontFamily = segoe_ui,
                    fontSize = 12.sp
                )

            }
            // поле дял ввода пароля
            Box(
                modifier = Modifier
                    .requiredSize(boxWidth, boxHeight)
                    .padding(
                        top = boxHeight / 11 * 4 + 5.dp,
                        start = boxWidth / 11,
                        end = boxWidth / 11,
                        bottom = boxHeight / 11 * 6 + 5.dp

                    )
                    .background(CustomEnterBarColor, shape = RoundedCornerShape(10.dp))
            ){
                Text(
                    text = "введите пароль",
                    modifier = Modifier
                        .alpha(0.5f)
                        .padding(
                            start = 5.dp,
                            top = 5.dp
                        ),
                    color = CustomGrey,
                    fontFamily = segoe_ui,
                    fontSize = 12.sp
                )

            }
            // поле "повторите пароль"
            Box(
                modifier = Modifier
                    .requiredSize(boxWidth, boxHeight)
                    .padding(
                        top = boxHeight / 11 * 5 + 5.dp,
                        start = boxWidth / 11,
                        end = boxWidth / 11,
                        bottom = boxHeight / 11 * 5 + 5.dp

                    )
                    .background(CustomEnterBarColor, shape = RoundedCornerShape(10.dp))
            ){
                Text(
                    text = "повторите пароль",
                    modifier = Modifier
                        .alpha(0.5f)
                        .padding(
                            start = 5.dp,
                            top = 5.dp
                        ),
                    color = CustomGrey,
                    fontFamily = segoe_ui,
                    fontSize = 12.sp
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
            ){
                Text(
                    text = "Вход",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            top = 5.dp
                        ),
                    color = CustomTrafficWhite,
                    fontFamily = segoe_ui,
                    fontSize = 16.sp
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
            ){
                Text(
                    text = "Регистрация",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(
                            top = 5.dp
                        ),
                    color = CustomTrafficWhite,
                    fontFamily = segoe_ui,
                    fontSize = 16.sp
                )

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    METTheme {
        BackgroundRegistrationScreen()
    }
}