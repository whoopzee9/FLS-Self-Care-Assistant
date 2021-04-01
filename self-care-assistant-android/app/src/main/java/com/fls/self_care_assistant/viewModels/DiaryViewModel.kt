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

    var emotionsList = ArrayList<String>()

    var repository = DiaryRepository.instance

    fun seekBarTracking(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        println(progress)
        repository.selectedEmotionStrength.postValue(progress)

    }

    fun addNewEmotion(view: View) {
        val emotionStr = emotionsList[getSpinnerPosition().value!!]
        val date = Calendar.getInstance().time
        val emotion = Emotion(date, emotionStr, getEmotionStrength().value!! + 1)
        repository.addNewEmotion(emotion)
    }

    fun getEmotionDiary(): LiveData<ArrayList<Emotion>> {
        return repository.emotionDiary
    }

    fun setSpinnerPosition(value: Int) {
        repository.selectedEmotionPosition.postValue(value)
    }

    fun getSpinnerPosition(): LiveData<Int> {
        return repository.selectedEmotionPosition
    }

    fun getEmotionStrength(): LiveData<Int> {
        return repository.selectedEmotionStrength
    }
}