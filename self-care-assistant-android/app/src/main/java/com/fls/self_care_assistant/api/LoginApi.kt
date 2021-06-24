package com.fls.self_care_assistant.api

import com.fls.self_care_assistant.data.AuthBody
import com.fls.self_care_assistant.data.ResponseMessage
import com.fls.self_care_assistant.data.TokenResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("/api/v1/signin")
    suspend fun signIn(@Body authBody: AuthBody) : Response<TokenResponse>
}