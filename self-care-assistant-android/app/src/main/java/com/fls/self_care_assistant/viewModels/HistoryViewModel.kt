package com.fls.self_care_assistant.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.repositories.DiaryRepository
import kotlinx.coroutines.flow.StateFlow

class HistoryViewModel: ViewModel() {

    private val repository = DiaryRepository.instance
    val emotionDiary: StateFlow<List<Emotion>> = repository._emotionDiary

}