package com.nalldev.collywood.presentation.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.nalldev.collywood.domain.repository.SessionRepository
import kotlinx.coroutines.launch

class OnBoardingViewModel (private val sessionManager: SessionRepository) : ViewModel() {
    fun onBoardingSaw() {
        viewModelScope.launch {
            sessionManager.setOnBoardingSaw(true)
        }
    }

    fun getOnBoardingSaw() : LiveData<Boolean> = liveData {
        emit(sessionManager.getOnBoardingSaw())
    }
}