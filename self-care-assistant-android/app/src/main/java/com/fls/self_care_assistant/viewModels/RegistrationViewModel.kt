package com.fls.self_care_assistant.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fls.self_care_assistant.repositories.RegistrationRepository

class RegistrationViewModel : ViewModel() {
    var repository = RegistrationRepository.instance


    fun getEmail(): LiveData<String> = repository.email
    fun getName(): LiveData<String> = repository.userName

    fun getPassword(): LiveData<String> = repository.password
    fun getConfirmPassword(): LiveData<String> = repository.confirmPassword

}