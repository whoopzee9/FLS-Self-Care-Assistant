package com.fls.self_care_assistant.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.network.ErrorEntity
import com.fls.self_care_assistant.network.NetworkResult
import com.fls.self_care_assistant.repositories.DiaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel: ViewModel() {

    private val repository = DiaryRepository.instance
    val emotionDiary: StateFlow<List<Emotion>> = repository._emotionDiary

    private val _historyState: MutableStateFlow<HistoryState> = MutableStateFlow(HistoryState.Initial)
    val historyState: StateFlow<HistoryState> get() = _historyState

    fun getEmotionHistory() {
        viewModelScope.launch {
            //_addEmotionState.value = AddEmotionState.Processing
            val result = repository.getEmotionDiary()
            when (result) {
                is NetworkResult.Error -> {
                    _historyState.value = HistoryState.Failure(result.error)
                }
                is NetworkResult.Success -> {
                    //_addEmotionState.value = AddEmotionState.Success
                }
            }
        }
    }

    fun resetHistoryState() {
        _historyState.value = HistoryState.Initial
    }

    fun deleteEmotion(id: String) {
        viewModelScope.launch {
            _historyState.value = HistoryState.Processing
            val result = repository.deleteEmotion(id)
            when (result) {
                is NetworkResult.Error -> {
                    _historyState.value = HistoryState.Failure(result.error)
                }
                is NetworkResult.Success -> {
                    _historyState.value = HistoryState.Success
                }
            }
        }
    }

    sealed class HistoryState {
        object Initial : HistoryState()
        object Processing : HistoryState()
        object Success : HistoryState()
        data class Failure(val historyError: ErrorEntity) : HistoryState()
    }
}