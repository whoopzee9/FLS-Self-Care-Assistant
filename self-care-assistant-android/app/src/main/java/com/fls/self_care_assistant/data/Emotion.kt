package com.fls.self_care_assistant.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.util.*

@Parcelize
data class Emotion(var id: String, var date: Date, var emotion: EmotionType, var intensity: Int): Parcelable