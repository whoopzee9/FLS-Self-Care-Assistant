package com.fls.self_care_assistant.repositories

import androidx.lifecycle.MutableLiveData
import com.fls.self_care_assistant.api.DiaryApi
import com.fls.self_care_assistant.api.LoginApi
import com.fls.self_care_assistant.data.AuthBody
import com.fls.self_care_assistant.data.RegistrationBody
import com.fls.self_care_assistant.data.TokenResponse
import com.fls.self_care_assistant.network.LoginError
import com.fls.self_care_assistant.network.NetworkResult
import com.fls.self_care_assistant.network.RegistrationError
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginRepository {
    companion object {
        val instance: LoginRepository by lazy { Repo.Instance }
    }

    private object Repo {
        val Instance = LoginRepository()
    }

    private val BASE_URL = "https://fls-self-care-assistant.herokuapp.com"
    private var retrofitApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(LoginApi::class.java)

    var email: MutableLiveData<String> = MutableLiveData()
    var password: MutableLiveData<String> = MutableLiveData()

    var emailRestore: MutableLiveData<String> = MutableLiveData()

    suspend fun signIn(authBody: AuthBody) : NetworkResult<TokenResponse> {
        val response = retrofitApi.signIn(authBody)
        if (response.isSuccessful) {
            return NetworkResult.Success(response.body()!!)
        } else if (response.code() == 401) {
            return NetworkResult.Error(LoginError.InvalidCredentials)
        } else {
            return NetworkResult.Error(LoginError.Unknown)
        }
    }
}