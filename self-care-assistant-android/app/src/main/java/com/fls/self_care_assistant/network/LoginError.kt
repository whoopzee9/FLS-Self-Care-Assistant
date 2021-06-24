package com.fls.self_care_assistant.network

sealed class LoginError: ErrorEntity {
    object InvalidCredentials: LoginError()
    object Unknown: LoginError()
}