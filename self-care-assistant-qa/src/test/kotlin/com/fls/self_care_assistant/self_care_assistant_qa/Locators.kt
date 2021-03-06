package com.fls.self_care_assistant.self_care_assistant_qa

class LoginPageLocators {
    companion object {
        const val MAIN_WINDOW = "div.Auth_main__34UPb"
        const val SIGN_IN_PAGE = ".Auth_route_btn__1qFZL[value=\"singIn\"]"
        const val SIGN_UP_PAGE = ".Auth_route_btn__1qFZL[value=\"singUp\"]"
        const val SELECTED_PAGE = "button.Auth_active_route_btn__yNFuj"
    }
}

class SignInLocators {
    companion object {
        const val LOGIN_INPUT = ".Auth_input__2ImVd[placeholder=\"login\"]"
        const val PASS_INPUT = ".Auth_input__2ImVd[placeholder=\"password\"]"
        const val SIGN_IN_BUTTON = "input.Auth_auth_btn__2Kn9V"
        const val FORGOT_PASSWORD_BUTTON = "button.Auth_btn_password__1chKn"
        const val GOOGLE_BUTTON = "button.Auth_social_btn__1FTJF"
        const val YANDEX_BUTTON = "button.Auth_social_btn__1FTJF"
    }
}

class SignUpLocators {
    companion object {
        const val NAME_INPUT = ".Auth_input__2ImVd[placeholder=\"name\"]"
        const val EMAIL_INPUT = ".Auth_input__2ImVd[placeholder=\"email\"]"
        const val PASS_INPUT = ".Auth_input__2ImVd[placeholder=\"password\"]"
        const val CONFIRM_PASS_INPUT = ".Auth_input__2ImVd[placeholder=\"confirm password\"]"
        const val PRIVACY_POLICY = "#checkbox"
        const val CONFIRM_SIGN_UP_BUTTON = "input.Auth_auth_btn__2Kn9V"
    }
}
