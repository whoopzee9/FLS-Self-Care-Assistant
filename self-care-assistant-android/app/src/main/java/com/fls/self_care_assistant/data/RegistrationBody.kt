package com.fls.self_care_assistant.data

data class RegistrationBody(val name: String, val email: String, val password: String, val roles: List<Role>)