package com.nalldev.collywood.presentation.util

sealed class RequestState <out R> private constructor(){
    data class Success <out T>(val data: T) : RequestState<T>()
    data class Error(val message: String) : RequestState<Nothing>()
    data object Loading: RequestState<Nothing>()
}