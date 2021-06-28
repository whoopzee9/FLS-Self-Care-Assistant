package com.fls.self_care_assistant.self_care_assistant_qa.pages

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import com.fls.self_care_assistant.self_care_assistant_qa.SignInLocators
import com.fls.self_care_assistant.self_care_assistant_qa.get
import org.junit.jupiter.api.Assertions
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class SignInPage {
    companion object {
        fun fillLoginInput(login: String) {
            get(SignInLocators.LOGIN_INPUT).value = login
        }

        fun fillPassInput(pass: String) {
            get(SignInLocators.PASS_INPUT).value = pass
        }

        fun fillAllInputs(login: String, pass: String) {
            fillLoginInput(login)
            fillPassInput(pass)
        }

        fun confirmAuthorization() {
            get(SignInLocators.SIGN_IN_BUTTON).click()
        }

        fun checkSuccessfulAuthorization() {
            WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.urlMatches("http://localhost:3000/content"))
        }

        fun checkIncorrectDataAlert() {
            Assertions.assertEquals("Неверные данные", Selenide.switchTo().alert().text)
        }
    }
}
