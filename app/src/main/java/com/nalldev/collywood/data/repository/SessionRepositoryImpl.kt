package com.nalldev.collywood.data.repository

import android.content.SharedPreferences
import androidx.core.content.edit
import com.nalldev.collywood.domain.repository.SessionRepository

class SessionRepositoryImpl(private val pref : SharedPreferences) : SessionRepository {
    override suspend fun setIsLogin(isLogin: Boolean) {
        pref.edit {
            putBoolean("is_login", isLogin)
            apply()
        }
    }

    override suspend fun getIsLogin(): Boolean {
        return pref.getBoolean("is_login", false)
    }

    override suspend fun setOnBoardingSaw(wasSaw: Boolean) {
        pref.edit {
            putBoolean("saw_onboarding", wasSaw)
            apply()
        }
    }

    override suspend fun getOnBoardingSaw(): Boolean {
        return pref.getBoolean("saw_onboarding", false)
    }
}