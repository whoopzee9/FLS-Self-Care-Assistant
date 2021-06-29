package com.fls.self_care_assistant.repositories

import androidx.lifecycle.MutableLiveData
import com.fls.self_care_assistant.api.DiaryApi
import com.fls.self_care_assistant.api.RegistrationApi
import com.fls.self_care_assistant.data.RegistrationBody
import com.fls.self_care_assistant.network.NetworkResult
import com.fls.self_care_assistant.network.RegistrationError
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegistrationRepository {
    companion object {
        val instance: RegistrationRepository by lazy { Repo.Instance }
    }

    private object Repo {
        val Instance = RegistrationRepository()
    }

    private val BASE_URL = "https://fls-self-care-assistant.herokuapp.com"
    private var retrofitApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(RegistrationApi::class.java)

    var email: MutableLiveData<String> = MutableLiveData()
    var userName: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()
    var confirmPassword: MutableLiveData<String> = MutableLiveData()
    val isChecked: MutableLiveData<Boolean> = MutableLiveData()

    init {
        isChecked.postValue(false)
    }

    suspend fun signUp(registrationBody: RegistrationBody) : NetworkResult<Any> {
        val response = retrofitApi.signUp(registrationBody)
        if (response.isSuccessful) {
            return NetworkResult.Success(response)
        } else if (response.code() == 404) {
            return NetworkResult.Error(RegistrationError.NotFound)
        } else {
            return NetworkResult.Error(RegistrationError.Unknown)
        }
    }
}