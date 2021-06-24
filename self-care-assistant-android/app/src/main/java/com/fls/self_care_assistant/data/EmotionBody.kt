package com.fls.self_care_assistant.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmotionBody(var createDate: String, var emotionName: EmotionType, var intensity: String): Parcelable