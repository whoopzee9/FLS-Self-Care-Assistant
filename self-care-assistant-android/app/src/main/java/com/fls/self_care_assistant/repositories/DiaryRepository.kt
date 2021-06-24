package com.fls.self_care_assistant.repositories

import androidx.lifecycle.MutableLiveData
import com.fls.self_care_assistant.api.DiaryApi
import com.fls.self_care_assistant.data.Emotion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DiaryRepository {
    companion object {
        val instance : DiaryRepository by lazy { holder.Instance }
    }

    private object holder {
        val Instance = DiaryRepository()
    }

    var emotionDiary: MutableLiveData<ArrayList<Emotion>> = MutableLiveData()
    var selectedEmotionPosition: MutableLiveData<Int> = MutableLiveData()
    var selectedEmotionStrength: MutableLiveData<Int> = MutableLiveData()

    private val BASE_URL = "https://fls-self-care-assistant.herokuapp.com"
    private var retrofitApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DiaryApi::class.java)

    init {
        selectedEmotionPosition.postValue(0)
        selectedEmotionStrength.postValue(0)
    }

    fun getEmotionDiary() {
        //TODO get from DB
    }

    fun addNewEmotion(emotion: Emotion) {
        //TODO add to DB
        var array = emotionDiary.value
        if (array == null) {
            array = ArrayList()
        }
        array.add(emotion)
        emotionDiary.postValue(array)
    }

}