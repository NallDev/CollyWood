package com.nalldev.collywood.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.nalldev.collywood.domain.model.UserModel
import com.nalldev.collywood.domain.repository.SessionRepository

class SessionRepositoryImpl(private val pref : SharedPreferences) : SessionRepository {

    override fun setUser(user: UserModel) {
        val gson = Gson()
        val json = gson.toJson(user)
        pref.edit {
            putString("user", json)
            apply()
        }
    }

    override fun getUser(): UserModel? {
        val gson = Gson()
        val json = pref.getString("user", null)
        return gson.fromJson(json, UserModel::class.java)
    }

    override fun setIsLogin(isLogin: Boolean) {
        pref.edit {
            putBoolean("is_login", isLogin)
            apply()
        }
    }

    override fun getIsLogin(): Boolean {
        return pref.getBoolean("is_login", false)
    }

    override fun setOnBoardingSaw(wasSaw: Boolean) {
        pref.edit {
            putBoolean("saw_onboarding", wasSaw)
            apply()
        }
    }

    override fun getOnBoardingSaw(): Boolean {
        return pref.getBoolean("saw_onboarding", false)
    }

    override fun clearLoginSession(firstKey: String, secondKey: String) {
        pref.edit().remove(firstKey).remove(secondKey).apply()
    }
}