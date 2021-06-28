package com.fls.self_care_assistant.self_care_assistant_qa.functional_tests

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner
import com.fls.self_care_assistant.self_care_assistant_qa.LoginPageLocators
import com.fls.self_care_assistant.self_care_assistant_qa.get
import com.fls.self_care_assistant.self_care_assistant_qa.pages.SignInPage
import com.fls.self_care_assistant.self_care_assistant_qa.pages.SignUpPage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.text.SimpleDateFormat
import java.util.*

class SignInPageTest {
    @BeforeEach
    fun setup() {
        Selenide.open("http://localhost:3000/login")
    }

    @AfterEach
    fun closeBrowser()
    {
        Selenide.closeWebDriver()
    }

    @Test
    fun authorizeCorrectUser() {
        SignInPage.fillAllInputs("12345@mail.ru", "12345")
        SignInPage.confirmAuthorization()
        SignInPage.checkSuccessfulAuthorization()
    }

    @Test
    fun authorizeUserWithIncorrectData() {
        SignInPage.fillAllInputs("aaaaaaaa@mail.ru", "aaaaaaaa")
        SignInPage.confirmAuthorization()
        SignInPage.checkIncorrectDataAlert()
    }

    @Test
    fun authorizeUserWithEmptyData() {
        SignInPage.confirmAuthorization()
        SignInPage.checkIncorrectDataAlert()
    }

    @Test
    fun authorizeNewlyRegisteredUser() {
        get(LoginPageLocators.SIGN_UP_PAGE).click()
        val sdf = SimpleDateFormat("hh.mm.ss")
        val email = "${sdf.format(Calendar.getInstance().time)}@mail.ru"
        SignUpPage.fillAllInputs("name", email, "pass", "pass")
        SignUpPage.acceptPrivacyPolicy()
        SignUpPage.confirmRegister()
        SignUpPage.checkCorrectRegisterAlert()
        WebDriverWait(WebDriverRunner.getWebDriver(), 5).until(ExpectedConditions.alertIsPresent()).accept()
        SignInPage.fillAllInputs(email, "pass")
        SignInPage.confirmAuthorization()
        SignInPage.checkSuccessfulAuthorization()
    }
}
