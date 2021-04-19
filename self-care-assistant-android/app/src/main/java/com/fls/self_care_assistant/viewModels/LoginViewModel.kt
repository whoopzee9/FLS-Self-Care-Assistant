package com.fls.self_care_assistant.viewModels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fls.self_care_assistant.repositories.LoginRepository


class LoginViewModel : ViewModel() {
    var repository = LoginRepository.instance


    fun getEmail(): LiveData<String> = repository.email

    fun getPassword(): LiveData<String> = repository.password

    fun validateInput() = !getEmail().value.isNullOrEmpty() && !getPassword().value.isNullOrEmpty()
}