package com.fls.self_care_assistant.data

data class FilterBody(var lhsDate: String, var rhsDate: String, var emotionNames: List<EmotionTypesFilterBody>)