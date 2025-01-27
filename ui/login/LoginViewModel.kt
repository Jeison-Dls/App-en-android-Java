package com.example.hospitalturnmanagement.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospitalturnmanagement.data.api.ApiClient
import com.example.hospitalturnmanagement.data.api.ApiService
import com.example.hospitalturnmanagement.data.model.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// ViewModel para gestionar la lógica de inicio de sesión
class LoginViewModel(private val apiService: ApiService) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _loginState = MutableStateFlow("")
    val loginState: StateFlow<String> get() = _loginState

    fun login(username: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val response = apiService.login(LoginRequest(username, password))
                withContext(Dispatchers.Main) { // Cambia al hilo principal para actualizar el estado
                    if (response.isSuccessful) {
                        _loginState.value = "Login Successful"
                        onSuccess()
                    } else {
                        _loginState.value = "Login Failed"
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _loginState.value = "Error: ${e.message}"
                }
            } finally {
                withContext(Dispatchers.Main) {
                    _isLoading.value = false
                }
            }
        }
    }
}