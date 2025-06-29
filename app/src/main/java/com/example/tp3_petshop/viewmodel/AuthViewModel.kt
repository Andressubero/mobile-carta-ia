package com.example.tp3_petshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp3_petshop.models.ChangePasswordRequest
import com.example.tp3_petshop.models.LoginRequest
import com.example.tp3_petshop.models.Register
import com.example.tp3_petshop.models.User
import com.example.tp3_petshop.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel()  {

    private val _user = MutableStateFlow<User?>(null)
    var user: StateFlow<User?> = _user
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun clearError(){
        _errorMessage.value = null
    }


    fun login(username: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.login(LoginRequest(username, password))
                if (response.isSuccessful) {
                    val success = getMe()
                    if (success) {
                        clearError()
                        onSuccess()
                    }
                } else {
                    _errorMessage.value = "Error ${response.code()}: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }


    fun register(username: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.register(Register(username, username, password))
                if (response.isSuccessful) {
                    delay(500)
                    getMe()
                    delay(500)
                    clearError()
                    onSuccess()
                } else {
                    _errorMessage.value = "Error ${response.code()}: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    suspend fun getMe(): Boolean {
        return try {
            val response = repository.getMe()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    _user.value = data
                    clearError()
                    true
                } else {
                    _errorMessage.value = "Error ${response.code()}: cuerpo vacío"
                    false
                }
            } else {
                _errorMessage.value = "Error ${response.code()}: ${response.message()}"
                false
            }
        } catch (e: Exception) {
            _errorMessage.value = "Error: ${e.message}"
            false
        }
    }

    fun changePassword(password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.changePassword(ChangePasswordRequest(password))
                if (response.isSuccessful) {
                    clearError()
                    onSuccess()
                } else {
                    _errorMessage.value = "Error ${response.code()}: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            }

        }

    }

    fun logout(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.logout()
                if (response.isSuccessful) {
                    clearError()
                    onSuccess()
                } else {
                    _errorMessage.value = "Error ${response.code()}: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            }

        }

    }

}