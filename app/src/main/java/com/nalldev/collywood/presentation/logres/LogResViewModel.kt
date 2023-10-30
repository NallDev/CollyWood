package com.nalldev.collywood.presentation.logres

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nalldev.collywood.domain.model.UserModel
import com.nalldev.collywood.domain.repository.SessionRepository
import com.nalldev.collywood.domain.repository.UserRepository
import kotlinx.coroutines.launch

class LogResViewModel (private val userRepository: UserRepository, private val sessionManager: SessionRepository) : ViewModel() {

    private val _loginResult: MutableLiveData<UserModel?> = MutableLiveData()
    val loginResult: LiveData<UserModel?> = _loginResult

    private val _registrationResult: MutableLiveData<Boolean> = MutableLiveData()
    val registrationResult: LiveData<Boolean> = _registrationResult

    private val _isUserLogin: MutableLiveData<Boolean> = MutableLiveData()
    val isUserLogin: LiveData<Boolean> = _isUserLogin

    init {
        isLogin()
    }

    private fun isLogin() {
        viewModelScope.launch {
            _isUserLogin.postValue(sessionManager.getIsLogin())
        }
    }

    fun setUserLogin() {
        viewModelScope.launch {
            sessionManager.setIsLogin(true)
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.login(username, password)
            _loginResult.postValue(user)
        }
    }

    fun register(email: String, phone: String, password: String) {
        viewModelScope.launch {
            val isCreated = userRepository.register(UserModel(email = email, phone = phone, password = password))
            _registrationResult.postValue(isCreated)
        }
    }
}