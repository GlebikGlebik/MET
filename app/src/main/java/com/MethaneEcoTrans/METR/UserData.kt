package com.MethaneEcoTrans.METR

import android.util.Log

var userDataset: MutableMap<String, List<String>> = mutableMapOf()



object UserData {
    var name: String = ""
    var surname: String = ""
    var password: String = ""
    var email: String = ""
    var uid: String = ""
}


public fun userDataUpdate(): MutableMap<String, List<String>>{
    userDataset.getOrPut(UserData.email) {mutableListOf(
        UserData.surname,
        UserData.name,
        UserData.password,
        UserData.uid)
    }
    Log.d("userDataset", "Датасет с данными пользователей: $userDataset") // Логируем данны
    return userDataset
}

fun main(){
    println(userDataUpdate())
}