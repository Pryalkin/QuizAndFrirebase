package com.bsuir.myquizwithfirebase.repository

import com.bsuir.myquizwithfirebase.setting.AppSettings

class Repository(
    private val appSettings: AppSettings
){

    fun setUsername(username: String) {
        appSettings.setCurrentUsername(username)
    }

    fun setResult(result: Int) {
        appSettings.setCurrentResult(result)
    }

    fun getUsername(): String? {
        return appSettings.getCurrentUsername()
    }


    fun getResult(): Int? {
        return appSettings.getCurrentResult()
    }

}