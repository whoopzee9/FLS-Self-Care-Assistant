package com.fls.self_care_assistant.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmotionType(val id: String, val name: String) : Parcelable {
    override fun toString(): String {
        return name
    }
}