package com.fls.self_care_assistant.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fls.self_care_assistant.data.EmotionType
import com.fls.self_care_assistant.data.EmotionTypesFilterBody
import com.fls.self_care_assistant.data.FilterBody
import com.fls.self_care_assistant.network.ErrorEntity
import com.fls.self_care_assistant.network.NetworkResult
import com.fls.self_care_assistant.repositories.DiaryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FilterViewModel : ViewModel() {

    var repository = DiaryRepository.instance

    private val _filterState: MutableStateFlow<FilterState> = MutableStateFlow(FilterState.Initial)
    val filterState: StateFlow<FilterState> get() = _filterState

    private val _filter: MutableStateFlow<FilterBody> = MutableStateFlow(FilterBody("", "", mutableListOf()))
    val filter: StateFlow<FilterBody> get() = _filter


    var emotionTypes : List<EmotionType> = mutableListOf()
    //TODO temp solution, change!!
    lateinit var anxiety : EmotionTypesFilterBody
    lateinit var anger : EmotionTypesFilterBody
    lateinit var apathy : EmotionTypesFilterBody

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
                    emotionTypes = result.data
                    emotionTypes.forEach {
                        if (it.name == "тревога") {
                            anger = EmotionTypesFilterBody(it, "", "")
                        }
                        if (it.name == "апатия") {
                            apathy = EmotionTypesFilterBody(it, "", "")
                        }
                        if (it.name == "злость") {
                            anxiety = EmotionTypesFilterBody(it, "", "")
                        }
                    }
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
            filter.collectLatest {
                if (it.emotionNames.isEmpty()) {
                    apathy.lhsIntensity = "1"
                    apathy.rhsIntensity = "10"
                    it.emotionNames.add(apathy)
                    anxiety.lhsIntensity = "1"
                    anxiety.rhsIntensity = "10"
                    it.emotionNames.add(anxiety)
                    apathy.lhsIntensity = "1"
                    apathy.rhsIntensity = "10"
                    it.emotionNames.add(apathy)
                }
                val result = repository.applyFilter(it)
                when (result) {
                    is NetworkResult.Error -> {
                        _filterState.value = FilterState.Failure(result.error)
                    }
                    is NetworkResult.Success -> {
                        _filterState.value = FilterState.Success
                    }
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

    fun addEmotion(emotionTypesFilterBody: EmotionTypesFilterBody) {
        _filter.value.emotionNames.add(emotionTypesFilterBody)
    }

    fun resetFilterState() {
        _filterState.value = FilterState.Initial
    }

    sealed class FilterState {
        object Initial : FilterState()
        object Processing : FilterState()
        object Success : FilterState()
        data class Failure(val filterError: ErrorEntity) : FilterState()
    }
}