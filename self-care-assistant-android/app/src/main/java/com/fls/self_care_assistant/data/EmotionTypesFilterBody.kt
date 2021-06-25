package com.fls.self_care_assistant.data

data class EmotionTypesFilterBody(val emotionName: EmotionType, var lhsIntensity: String, var rhsIntensity: String)