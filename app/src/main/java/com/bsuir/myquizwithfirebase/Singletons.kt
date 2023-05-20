package com.bsuir.myquizwithfirebase

import android.content.Context
import com.bsuir.myquizwithfirebase.repository.Repository
import com.bsuir.myquizwithfirebase.setting.AppSettings
import com.bsuir.myquizwithfirebase.setting.SharedPreferencesAppSettings

object Singletons {

    private lateinit var appContext: Context

    val appSettings: AppSettings by lazy {
        SharedPreferencesAppSettings(appContext)
    }

    val repository: Repository by lazy {
        Repository(
            appSettings = appSettings
        )
    }

    fun init(appContext: Context) {
        Singletons.appContext = appContext
    }

}