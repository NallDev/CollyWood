package com.nalldev.collywood.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalldev.collywood.domain.repository.SessionRepository
import kotlinx.coroutines.launch

class ProfileViewModel (private val sessionManager : SessionRepository) : ViewModel() {
    fun logOut() {
        viewModelScope.launch {
            sessionManager.setIsLogin(false)
        }
    }
}