package com.nalldev.collywood.domain.repository

import com.nalldev.collywood.domain.model.UserModel

interface SessionRepository {
    fun setUser(user: UserModel)
    fun getUser() : UserModel?
    fun setIsLogin(isLogin: Boolean)
    fun getIsLogin() : Boolean
    fun setOnBoardingSaw(wasSaw : Boolean)
    fun getOnBoardingSaw() : Boolean
    fun clearLoginSession(firstKey: String, secondKey: String)
}