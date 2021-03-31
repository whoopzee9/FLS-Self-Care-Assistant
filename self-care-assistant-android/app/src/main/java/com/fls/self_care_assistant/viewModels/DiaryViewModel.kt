package com.fls.self_care_assistant.viewModels

import android.view.View
import android.widget.SeekBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.repositories.DiaryRepository
import java.util.*
import kotlin.collections.ArrayList

class DiaryViewModel : ViewModel() {
    var selectedEmotionPosition: Int = 0
    var selectedEmotionStrength: Int = 1
    var emotionsList = ArrayList<String>()

    var repository = DiaryRepository.instance

    fun seekBarTracking(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        selectedEmotionStrength = progress + 1

    }

    fun addNewEmotion(view: View) {
        val emotionStr = emotionsList[selectedEmotionPosition]
        val date = Calendar.getInstance().time
        val emotion = Emotion(date, emotionStr, selectedEmotionStrength)
        repository.addNewEmotion(emotion)
    }

    fun getEmotionDiary(): LiveData<ArrayList<Emotion>> {
        return repository.emotionDiary
    }

}