package com.fls.self_care_assistant.viewModels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fls.self_care_assistant.data.RegistrationBody
import com.fls.self_care_assistant.network.ErrorEntity
import com.fls.self_care_assistant.network.NetworkResult
import com.fls.self_care_assistant.network.RegistrationError
import com.fls.self_care_assistant.repositories.RegistrationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {
    var repository = RegistrationRepository.instance

    private val _registrationState: MutableStateFlow<RegistrationState> = MutableStateFlow(RegistrationState.Initial)
    val registrationState: StateFlow<RegistrationState> get() = _registrationState

    fun getEmail(): LiveData<String> = repository.email
    fun getUserName(): LiveData<String> = repository.userName

    fun getPassword(): LiveData<String> = repository.password
    fun getConfirmPassword(): LiveData<String> = repository.confirmPassword

    fun getPrivacyStatus(): LiveData<Boolean> = repository.isChecked

    fun validateInput(): Boolean = !getEmail().value.isNullOrEmpty()
            && !getPassword().value.isNullOrEmpty()
            && !getUserName().value.isNullOrEmpty() && !getConfirmPassword().value.isNullOrEmpty()

    fun passwordsMatch(): Boolean = validateInput()
            && getPassword().value.toString() == getConfirmPassword().value.toString()

    fun isEmail(): Boolean = Patterns.EMAIL_ADDRESS.matcher(getEmail().value.toString()).matches()
    fun isAcceptPrivacy(): Boolean = getPrivacyStatus().value!!

    fun signUp() {
        viewModelScope.launch {
            _registrationState.value = RegistrationState.Processing
            val response = repository.signUp(
                RegistrationBody(
                    getUserName().value.orEmpty(),
                    getEmail().value.orEmpty(),
                    getPassword().value.orEmpty(),
                    emptyList()
                )
            )
            when (response) {
                is NetworkResult.Success -> {
                    _registrationState.value = RegistrationState.Success
                }
                is NetworkResult.Error -> {
                    _registrationState.value = RegistrationState.Failure(response.error)
                }
            }
        }
    }

    sealed class RegistrationState {
        object Initial : RegistrationState()
        object Processing : RegistrationState()
        object Success : RegistrationState()
        data class Failure(val registrationError: ErrorEntity) : RegistrationState()
    }
}