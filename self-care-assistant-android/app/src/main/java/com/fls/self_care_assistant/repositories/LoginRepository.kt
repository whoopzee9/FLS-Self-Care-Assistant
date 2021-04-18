package com.fls.self_care_assistant.repositories

import androidx.lifecycle.MutableLiveData

class LoginRepository {
    companion object {
        val instance: LoginRepository by lazy { Repo.Instance }
    }

    private object Repo {
        val Instance = LoginRepository()
    }

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

}