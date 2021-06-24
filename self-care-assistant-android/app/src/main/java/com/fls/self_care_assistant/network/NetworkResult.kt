package com.fls.self_care_assistant.network

sealed class NetworkResult<T> {
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Error<T>(val error: ErrorEntity): NetworkResult<T>()
}