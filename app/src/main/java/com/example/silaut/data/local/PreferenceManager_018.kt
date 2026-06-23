package com.example.silaut.data.local

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager_018(context: Context) {
    private val prefs_018: SharedPreferences = context.getSharedPreferences("silaut_prefs_018", Context.MODE_PRIVATE)

    fun setUserLogin_018(userId_018: Int, role_018: String) {
        prefs_018.edit().apply {
            putInt("userId_018", userId_018)
            putString("role_018", role_018)
            putBoolean("isLoggedIn_018", true)
            apply()
        }
    }

    fun isLoggedIn_018(): Boolean = prefs_018.getBoolean("isLoggedIn_018", false)
    fun getUserId_018(): Int = prefs_018.getInt("userId_018", -1)
    fun getUserRole_018(): String? = prefs_018.getString("role_018", "USER")

    fun logout_018() {
        prefs_018.edit().clear().apply()
    }
}
