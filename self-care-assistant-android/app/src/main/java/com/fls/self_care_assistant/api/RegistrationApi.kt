package com.fls.self_care_assistant.api

import com.fls.self_care_assistant.data.RegistrationBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationApi {
    @POST("/api/v1/signup")
    suspend fun signUp(@Body registrationBody: RegistrationBody): Response<Void>
}