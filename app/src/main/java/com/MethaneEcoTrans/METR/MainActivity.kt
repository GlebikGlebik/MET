package com.MethaneEcoTrans.METR

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.MethaneEcoTrans.METR.theme.METTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.clickable
import com.MethaneEcoTrans.METR.theme.CustomTurquoiseBlue
import com.MethaneEcoTrans.METR.theme.CustomTrafficWhite
import com.MethaneEcoTrans.METR.theme.CustomCarpiBlue
import com.MethaneEcoTrans.METR.theme.CustomEnterBarColor
import com.MethaneEcoTrans.METR.theme.CustomGrey


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
                }

            }
        }
    }
}

