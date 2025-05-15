package com.MethaneEcoTrans.METR

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

@Composable
fun RegisterActivity(
    email: String,
    password: String,
    onRegistrationSuccess: (FirebaseUser ) -> Unit,
    onRegistrationFailure: (String) -> Unit
) {
    val auth = FirebaseAuth.getInstance()

    LaunchedEffect(Unit) {
        try {
            // Регистрация пользователя
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            val user = result.user

            // Отправка письма с подтверждением
            user?.sendEmailVerification()?.await()

            // Успешная регистрация
            if (user != null) {
                UserData.uid = user.uid  // Получаем уникальный идентификатор пользователя
                Log.d("RegisterActivity", "UID: ${UserData.uid}")


                onRegistrationSuccess(user)
            } else {
                onRegistrationFailure("Ошибка: пользователь не создан.")
            }
        } catch (e: Exception) {
            Log.e("RegisterActivity", "Ошибка регистрации: ${e.message}")
            onRegistrationFailure("Ошибка регистрации: ${e.message}")
        }
    }
    userDataUpdate()
}
