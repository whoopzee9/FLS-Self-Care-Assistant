package com.fls.self_care_assistant.repositories

import androidx.lifecycle.MutableLiveData

class RegistrationRepository {
    companion object {
        val instance: RegistrationRepository by lazy { Repo.Instance }
    }

    private object Repo {
        val Instance = RegistrationRepository()
    }

    var email: MutableLiveData<String> = MutableLiveData()
    var userName: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var confirmPassword: MutableLiveData<String> = MutableLiveData()

}