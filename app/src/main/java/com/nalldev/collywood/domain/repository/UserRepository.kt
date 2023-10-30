package com.nalldev.collywood.domain.repository

import com.nalldev.collywood.domain.model.UserModel

interface UserRepository {
    suspend fun login(username: String, password: String) : UserModel?

    suspend fun register(user : UserModel) : Boolean
}