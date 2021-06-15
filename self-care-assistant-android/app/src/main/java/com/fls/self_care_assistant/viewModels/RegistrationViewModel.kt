package com.fls.self_care_assistant.viewModels

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fls.self_care_assistant.repositories.RegistrationRepository

class RegistrationViewModel : ViewModel() {
    var repository = RegistrationRepository.instance


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

}