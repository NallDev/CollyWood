package com.nalldev.collywood.domain.model

data class UserModel(
    val id : Int = -1,
    val email : String,
    val phone : String,
    val password: String,
)
