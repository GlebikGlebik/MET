package com.MethaneEcoTrans.METR

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.compose.rememberNavController
import com.MethaneEcoTrans.METR.theme.METTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.ActionCodeSettings

val segoe_ui_bold = FontFamily(
    Font(R.font.segoe_ui_bold)
)

val segoe_ui = FontFamily(
    Font(R.font.segoe_ui)
)

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            METTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "enterScreen"){
                    composable("EnterScreen") { EnterScreen(navController) }
                    composable("RegistrationScreen") { RegistrationScreen(navController) }
                    composable("MainScreen") {MainScreen(navController)}
                }

            }
        }
    }

}

