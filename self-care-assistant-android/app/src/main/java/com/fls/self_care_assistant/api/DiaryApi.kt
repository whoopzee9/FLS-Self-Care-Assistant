package com.fls.self_care_assistant.api

import com.fls.self_care_assistant.data.*
import retrofit2.Response
import retrofit2.http.*

interface DiaryApi {
    @GET("/api/v1/emotion")
    suspend fun getDiary(@Header("Authorization") token: String) : Response<List<EmotionBody>>

    @GET("/api/v1/emotion/name")
    suspend fun getEmotionTypes(@Header("Authorization") token: String) : Response<List<EmotionType>>

    @POST("/api/v1/emotion")
    suspend fun saveEmotion(@Body emotion: EmotionBodyWithoutId, @Header("Authorization") token: String) : Response<ResponseMessage>

    @DELETE("/api/v1/emotion")
    suspend fun deleteEmotion(@Query("id") id: String, @Header("Authorization") token: String) : Response<ResponseMessage>

    @POST("/api/v1/emotion/filter")
    suspend fun applyFilter(@Body filterBody: FilterBody, @Header("Authorization") token: String) : Response<List<EmotionBody>>
}