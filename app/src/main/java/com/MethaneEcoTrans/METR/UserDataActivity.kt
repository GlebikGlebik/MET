package com.MethaneEcoTrans.METR

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

data class UserHistory(
    val history: MutableMap<String, MutableMap<String, List<String>>>
)

data class UserData(
    val email: String,
    val password: String,
    val surname: String,
    val name: String,
    val vehicles: List<String>
)

fun saveUserData(email: String, password: String, surname: String, name: String){
    val auth = FirebaseAuth.getInstance()
    val user  = auth.currentUser

    if (user  != null) {
        val uid = user.uid

        // Создание объекта UserData
        val userData = UserData(
                email = email,
                password = password,
                surname = surname,
                name = name,
                vehicles = emptyList() // Изначально пустой список
            )

        val userHistory = UserHistory(history = mutableMapOf())

        // Сохранение данных пользователя в Realtime Database
        val database = FirebaseDatabase.getInstance()
        val userDataRef = database.getReference("users").child(uid)
        val userHistoryRef = database.getReference("history").child(uid)

        userDataRef.setValue(userData).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("Данные пользователя успешно сохранены.")
            } else {
                println("Ошибка при сохранении данных: ${task.exception?.message}")
            }
        }
        userHistoryRef.setValue(userHistory).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                println("История пользователя успешно инициализирована.")
            } else {
                println("Ошибка при инициализации истории: ${task.exception?.message}")
            }
        }
    } else {
        println("Пользователь не аутентифицирован.")
    }
}
