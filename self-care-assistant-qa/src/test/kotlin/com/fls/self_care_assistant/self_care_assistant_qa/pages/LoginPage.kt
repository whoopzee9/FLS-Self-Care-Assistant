package com.fls.self_care_assistant.self_care_assistant_qa.pages

import com.fls.self_care_assistant.self_care_assistant_qa.LoginPageLocators
import com.fls.self_care_assistant.self_care_assistant_qa.get
import org.junit.jupiter.api.Assertions

class LoginPage {
    companion object {
        fun checkSelectedPage(tmpValue: String) {
            Assertions.assertEquals(get(LoginPageLocators.SELECTED_PAGE).getAttribute("value"), tmpValue)
        }
    }
}
