package com.fls.self_care_assistant.network

sealed class DiaryError: ErrorEntity {
    object InvalidCredentials: DiaryError()
    object Unknown: DiaryError()
}