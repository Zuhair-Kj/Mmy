package com.example.mmy.androidApp

import android.content.Context
import android.content.SharedPreferences

const val KEY_USER_ID = "user_id"
class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("Prefs", Context.MODE_PRIVATE)
    fun getUserID(): String? {
        return sharedPreferences.getString(KEY_USER_ID, null)
    }

    fun setUserID(userId: String) {
        sharedPreferences.edit().putString(KEY_USER_ID, userId).apply()
    }
}