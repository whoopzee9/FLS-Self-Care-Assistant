package com.fls.self_care_assistant.network

sealed class FilterError: ErrorEntity {
    object InvalidCredentials: FilterError()
    object Unknown: FilterError()
}