package com.bsuir.myquizwithfirebase.setting

import android.content.Context

class SharedPreferencesAppSettings(
    appContext: Context
) : AppSettings {

    private val sharedPreferences = appContext.getSharedPreferences("settings", Context.MODE_PRIVATE)

    // Result
    override fun setCurrentResult(result: Int?) {
        val editor = sharedPreferences.edit()
        if (result == null)
            editor.remove(PREF_CURRENT_ACCOUNT_RESULT)
        else
            editor.putInt(PREF_CURRENT_ACCOUNT_RESULT, result)
        editor.apply()
    }

    override fun getCurrentResult(): Int? =
        sharedPreferences.getInt(PREF_CURRENT_ACCOUNT_RESULT, 0)

    // Username
    override fun getCurrentUsername(): String? =
        sharedPreferences.getString(PREF_CURRENT_ACCOUNT_USERNAME, null)


    override fun setCurrentUsername(username: String?) {
        val editor = sharedPreferences.edit()
        if (username == null)
            editor.remove(PREF_CURRENT_ACCOUNT_USERNAME)
        else
            editor.putString(PREF_CURRENT_ACCOUNT_USERNAME, username)
        editor.apply()
    }

    companion object {
        private const val PREF_CURRENT_ACCOUNT_USERNAME = "currentToken"
        private const val PREF_CURRENT_ACCOUNT_RESULT = "currentUsername"
    }

}