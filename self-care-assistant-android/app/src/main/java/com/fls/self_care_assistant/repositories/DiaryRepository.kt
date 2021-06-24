package com.fls.self_care_assistant.repositories

import androidx.lifecycle.MutableLiveData
import com.fls.self_care_assistant.api.DiaryApi
import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.data.EmotionBody
import com.fls.self_care_assistant.data.EmotionType
import com.fls.self_care_assistant.extensions.requestFormat
import com.fls.self_care_assistant.network.DiaryError
import com.fls.self_care_assistant.network.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat

class DiaryRepository {
    companion object {
        val instance: DiaryRepository by lazy { holder.Instance }
    }

    private object holder {
        val Instance = DiaryRepository()
    }

    val tokenRepository = TokenRepository.instance
    var _emotionDiary: MutableStateFlow<List<Emotion>> = MutableStateFlow(mutableListOf())
    var _selectedEmotionPosition: MutableLiveData<Int> = MutableLiveData()
    var _selectedEmotionStrength: MutableLiveData<Int> = MutableLiveData()

    private val BASE_URL = "https://fls-self-care-assistant.herokuapp.com"
    private var retrofitApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DiaryApi::class.java)

    init {
        _selectedEmotionPosition.postValue(0)
        _selectedEmotionStrength.postValue(0)
    }

    suspend fun getEmotionDiary(): NetworkResult<List<EmotionBody>> {
        val response = retrofitApi.getDiary(tokenRepository.getToken()!!)
        if (response.isSuccessful) {
            _emotionDiary.value = response.body()!!.map {
                Emotion(
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(it.createDate),
                    it.emotionName,
                    it.intensity.toInt()
                )
            }
            return NetworkResult.Success(response.body()!!)
        } else if (response.code() == 401) {
            return NetworkResult.Error(DiaryError.InvalidCredentials)
        } else {
            return NetworkResult.Error(DiaryError.Unknown)
        }
    }

    suspend fun addNewEmotion(emotion: Emotion): NetworkResult<Any> {
        val emotionBody =
            EmotionBody(emotion.date.requestFormat(), emotion.emotion, emotion.intensity.toString())
        val response = retrofitApi.saveEmotion(emotionBody, tokenRepository.getToken()!!)
        if (response.isSuccessful) {
            return NetworkResult.Success(response)
        } else if (response.code() == 401) {
            return NetworkResult.Error(DiaryError.InvalidCredentials)
        } else {
            return NetworkResult.Error(DiaryError.Unknown)
        }
    }

    suspend fun getEmotionTypes(): NetworkResult<List<EmotionType>> {
        val response = retrofitApi.getEmotionTypes(tokenRepository.getToken()!!)
        if (response.isSuccessful) {
            return NetworkResult.Success(response.body()!!)
        } else if (response.code() == 401) {
            return NetworkResult.Error(DiaryError.InvalidCredentials)
        } else {
            return NetworkResult.Error(DiaryError.Unknown)
        }
    }

}