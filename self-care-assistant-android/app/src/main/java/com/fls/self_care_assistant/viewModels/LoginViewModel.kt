package com.fls.self_care_assistant.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fls.self_care_assistant.data.AuthBody
import com.fls.self_care_assistant.network.ErrorEntity
import com.fls.self_care_assistant.network.NetworkResult
import com.fls.self_care_assistant.repositories.LoginRepository
import com.fls.self_care_assistant.repositories.TokenRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class LoginViewModel : ViewModel() {
    var repository = LoginRepository.instance
    var tokenRepository = TokenRepository.instance

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Initial)
    val loginState: StateFlow<LoginState> get() = _loginState

    fun getEmail(): LiveData<String> = repository.email
    fun getPassword(): LiveData<String> = repository.password

    fun validateInput() = !getEmail().value.isNullOrEmpty() && !getPassword().value.isNullOrEmpty()

    fun signUp() {
        viewModelScope.launch {
            _loginState.value = LoginState.Processing
            val response = repository.signIn(
                AuthBody(
                    getEmail().value.orEmpty(),
                    getPassword().value.orEmpty()
                )
            )
            when (response) {
                is NetworkResult.Success -> {
                    tokenRepository.saveToken(response.data.token)
                    _loginState.value = LoginState.Success
                }
                is NetworkResult.Error -> {
                    _loginState.value = LoginState.Failure(response.error)
                }
            }
        }
    }

    sealed class LoginState {
        object Initial : LoginState()
        object Processing : LoginState()
        object Success : LoginState()
        data class Failure(val loginError: ErrorEntity) : LoginState()
    }
}