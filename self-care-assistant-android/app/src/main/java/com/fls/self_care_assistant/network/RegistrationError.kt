package com.fls.self_care_assistant.network

sealed class RegistrationError: ErrorEntity {
    //object InvalidCredentials: RegistrationError()
    //object NotActivatedAccount: RegistrationError()
    object Unknown: RegistrationError()
    object NotFound: RegistrationError()
}