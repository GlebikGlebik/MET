package com.MethaneEcoTrans.METR

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.MethaneEcoTrans.METR.theme.METTheme

class main_screen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            METTheme {
                // Определяем метод BackgroundMainScreen
                BackgroundMainScreen()
            }
        }
    }
}

val segoe_ui_bold_main_screen = FontFamily(
    Font(R.font.segoe_ui_bold)
)

val segoe_ui_main_screen = FontFamily(
    Font(R.font.segoe_ui)
)

@Composable
fun BackgroundMainScreen() {

}