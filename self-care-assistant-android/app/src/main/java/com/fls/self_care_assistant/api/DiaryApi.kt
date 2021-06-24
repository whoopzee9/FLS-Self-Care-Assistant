package com.fls.self_care_assistant.api

import com.fls.self_care_assistant.data.Emotion
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface DiaryApi {
    @GET("/api/v1/emotion")
    fun getDiary(@Header("Authorization") token: String) : Response<List<Emotion>>

    @POST("/api/v1/emotion")
    fun saveEmotion(@Body emotion: Emotion, @Header("Authorization") token: String) : Response<Any>
}