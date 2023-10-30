package com.nalldev.collywood.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.nalldev.collywood.domain.repository.SessionRepository

class OnBoardingViewModel (private val sessionManager: SessionRepository) : ViewModel() {
    fun onBoardingSaw() {
        sessionManager.setOnBoardingSaw(true)
    }

    fun getOnBoardingSaw() : Boolean {
        return sessionManager.getOnBoardingSaw()
    }
}