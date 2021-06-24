package com.fls.self_care_assistant.api

import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.data.EmotionBody
import com.fls.self_care_assistant.data.EmotionType
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface DiaryApi {
    @GET("/api/v1/emotion")
    suspend fun getDiary(@Header("Authorization") token: String) : Response<List<Emotion>>

    @GET("/api/v1/emotion/name")
    suspend fun getEmotionTypes(@Header("Authorization") token: String) : Response<List<EmotionType>>

    @POST("/api/v1/emotion")
    suspend fun saveEmotion(@Body emotion: EmotionBody, @Header("Authorization") token: String) : Response<Any>
}