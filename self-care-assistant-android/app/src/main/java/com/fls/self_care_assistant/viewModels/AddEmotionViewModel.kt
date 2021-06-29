package com.fls.self_care_assistant.viewModels

import android.widget.SeekBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.data.EmotionType
import com.fls.self_care_assistant.data.EmotionTypesFilterBody
import com.fls.self_care_assistant.network.ErrorEntity
import com.fls.self_care_assistant.network.NetworkResult
import com.fls.self_care_assistant.repositories.DiaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

class AddEmotionViewModel: ViewModel() {

    var repository = DiaryRepository.instance

    private val _addEmotionState: MutableStateFlow<AddEmotionState> = MutableStateFlow(AddEmotionState.Initial)
    val addEmotionState: StateFlow<AddEmotionState> get() = _addEmotionState

    private val _emotionTypes: MutableStateFlow<List<EmotionType>> = MutableStateFlow(mutableListOf())
    val emotionTypes: StateFlow<List<EmotionType>> get() = _emotionTypes

    fun seekBarTracking(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
        println(progress)
        repository._selectedEmotionStrength.postValue(progress)

    }

    fun addNewEmotion(emotionCallback: (emotion: Emotion) -> Unit) {
        viewModelScope.launch {
            emotionTypes.collect {
                val emotionStr = it[getSpinnerPosition().value!!]
                val date = Calendar.getInstance().time
                val emotion = Emotion("", date, emotionStr, getEmotionStrength().value!! + 1)
                val result = repository.addNewEmotion(emotion)
                when (result) {
                    is NetworkResult.Error -> {
                        _addEmotionState.value = AddEmotionState.Failure(result.error)
                    }
                    is NetworkResult.Success -> {
                        _addEmotionState.value = AddEmotionState.Success
                        emotion.id = result.data.message
                        emotionCallback(emotion)
                    }
                }
            }
        }
    }

    fun setSpinnerPosition(value: Int) {
        repository._selectedEmotionPosition.postValue(value)
    }

    fun getSpinnerPosition(): LiveData<Int> {
        return repository._selectedEmotionPosition
    }

    fun getEmotionStrength(): LiveData<Int> {
        return repository._selectedEmotionStrength
    }

    fun getEmotionTypes() {
        viewModelScope.launch {
            //_addEmotionState.value = AddEmotionState.Processing
            val result = repository.getEmotionTypes()
            when (result) {
                is NetworkResult.Error -> {
                    _addEmotionState.value = AddEmotionState.Failure(result.error)
                }
                is NetworkResult.Success -> {
                    //_addEmotionState.value = AddEmotionState.Success
                    _emotionTypes.value = result.data
                }
            }
        }
    }

    fun getEmotionHistory() {
        viewModelScope.launch {
            //_addEmotionState.value = AddEmotionState.Processing
            val result = repository.getEmotionDiary()
            when (result) {
                is NetworkResult.Error -> {
                    _addEmotionState.value = AddEmotionState.Failure(result.error)
                }
                is NetworkResult.Success -> {
                    //_addEmotionState.value = AddEmotionState.Success
                }
            }
        }
    }

    fun resetAddEmotionState() {
        _addEmotionState.value = AddEmotionState.Initial
    }

    sealed class AddEmotionState {
        object Initial : AddEmotionState()
        object Processing : AddEmotionState()
        object Success : AddEmotionState()
        data class Failure(val addEmotionError: ErrorEntity) : AddEmotionState()
    }
}