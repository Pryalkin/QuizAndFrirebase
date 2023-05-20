package com.bsuir.myquizwithfirebase.setting

interface AppSettings {

    fun getCurrentUsername(): String?

    fun setCurrentUsername(token: String?)

    fun getCurrentResult(): Int?

    fun setCurrentResult(result: Int?)

}