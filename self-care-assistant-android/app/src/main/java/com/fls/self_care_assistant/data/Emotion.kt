package com.fls.self_care_assistant.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Emotion(var date: Date, var emotion: String, var intensity: Int): Parcelable