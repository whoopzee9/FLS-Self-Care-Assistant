package com.fls.self_care_assistant.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fls.self_care_assistant.data.Emotion
import com.fls.self_care_assistant.data.EmotionType
import com.fls.self_care_assistant.data.EmotionTypesFilterBody
import com.fls.self_care_assistant.data.FilterBody
import com.fls.self_care_assistant.network.ErrorEntity
import com.fls.self_care_assistant.network.NetworkResult
import com.fls.self_care_assistant.repositories.DiaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class FilterViewModel : ViewModel() {

    var repository = DiaryRepository.instance

    private val _filterState: MutableStateFlow<FilterState> = MutableStateFlow(FilterState.Initial)
    val filterState: StateFlow<FilterState> get() = _filterState

    private val _filter: MutableStateFlow<FilterBody> =
        MutableStateFlow(FilterBody("", "", mutableListOf()))
    val filter: StateFlow<FilterBody> get() = _filter

    private val _selectedEmotions: MutableStateFlow<List<EmotionTypesFilterBody>> =
        MutableStateFlow(listOf())
    val selectedEmotions: StateFlow<List<EmotionTypesFilterBody>> get() = _selectedEmotions

    private val _emotionTypes: MutableStateFlow<List<EmotionType>> =
        MutableStateFlow(listOf())
    val emotionTypes: StateFlow<List<EmotionType>> get() = _emotionTypes

    init {
        getEmotionTypes()
    }

    private fun getEmotionTypes() {
        viewModelScope.launch {
            val result = repository.getEmotionTypes()
            when (result) {
                is NetworkResult.Error -> {
                }
                is NetworkResult.Success -> {
                    _emotionTypes.value = result.data

                }
            }
        }
    }


    fun getEmotionHistory() {
        viewModelScope.launch {
            _filterState.value = FilterState.Processing
            val result = repository.getEmotionDiary()
            when (result) {
                is NetworkResult.Error -> {
                    _filterState.value = FilterState.Failure(result.error)
                }
                is NetworkResult.Success -> {
                    //_filterState.value = FilterState.Success
                }
            }
        }
    }

    fun applyFilter() {
        viewModelScope.launch {
            _filterState.value = FilterState.Processing
            if (selectedEmotions.value.isEmpty()) {
                _selectedEmotions.value = emotionTypes.value.map {
                    EmotionTypesFilterBody(it, "1", "10")
                }
            }
            _filter.value.emotionNames = selectedEmotions.value
            val result = repository.applyFilter(filter.value)
            when (result) {
                is NetworkResult.Error -> {
                    _filterState.value = FilterState.Failure(result.error)
                }
                is NetworkResult.Success -> {
                    repository._emotionDiary.value = result.data.map { body ->
                        Emotion(
                            body.id!!,
                            SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(body.createDate),
                            body.emotionName,
                            body.intensity.toInt()
                        )
                    }
                    _filterState.value = FilterState.Success
                }
            }
        }
    }

    fun resetFilter() {
        _filter.value = FilterBody("", "", mutableListOf())
    }

    fun setDateFrom(date: String) {
        _filter.value.lhsDate = date
    }

    fun setDateTo(date: String) {
        _filter.value.rhsDate = date
    }


    fun resetFilterState() {
        _filterState.value = FilterState.Initial
    }

    fun setSelectedEmotions(list: List<EmotionTypesFilterBody>) {
        _selectedEmotions.value = list
    }

    sealed class FilterState {
        object Initial : FilterState()
        object Processing : FilterState()
        object Success : FilterState()
        data class Failure(val filterError: ErrorEntity) : FilterState()
    }
}