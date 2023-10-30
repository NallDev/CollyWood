package com.nalldev.collywood.domain.repository

interface SessionRepository {
    suspend fun setIsLogin(isLogin: Boolean)
    suspend fun getIsLogin() : Boolean
    suspend fun setOnBoardingSaw(wasSaw : Boolean)
    suspend fun getOnBoardingSaw() : Boolean
}