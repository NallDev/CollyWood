package com.nalldev.collywood.data.db

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.nalldev.collywood.domain.model.UserModel

class SessionManager(context: Context) {
    private val pref by lazy {
        PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun setUser(user: UserModel) {
        val gson = Gson()
        val json = gson.toJson(user)
        pref.edit {
            putString("user", json)
            apply()
        }
    }

    fun getUser(): UserModel? {
        val gson = Gson()
        val json = pref.getString("user", null)
        return gson.fromJson(json, UserModel::class.java)
    }

    fun setIsLogin(isLogin: Boolean) {
        pref.edit {
            putBoolean("is_login", isLogin)
            apply()
        }
    }

    fun getIsLogin(): Boolean {
        return pref.getBoolean("is_login", false)
    }

    fun clearPreferences() {
        pref.edit().clear().apply()
    }
}