package com.fls.self_care_assistant.viewModels

import android.view.View
import android.widget.SeekBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.repositories.DiaryRepository
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DiaryViewModel : ViewModel() {

    var repository = DiaryRepository.instance

//    fun editEmotion(emotionId: Int, date: String, strength: Int, oldPosition: Int) {
//        val format = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
//        println(date)
//        println(format.parse(date))
//        println(emotionId)
//        println(emotionsList)
//        println(strength)
//        val emotion = Emotion(format.parse(date), emotionsList[emotionId], strength)
//        val list = getEmotionDiary().value
//        list?.set(oldPosition, emotion)
//        repository._emotionDiary.postValue(list)
//    }
}