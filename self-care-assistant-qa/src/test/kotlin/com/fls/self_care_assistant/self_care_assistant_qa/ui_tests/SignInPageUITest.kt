package com.fls.self_care_assistant.self_care_assistant_qa.ui_tests

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.fls.self_care_assistant.self_care_assistant_qa.*
import com.fls.self_care_assistant.self_care_assistant_qa.pages.LoginPage
import com.fls.self_care_assistant.self_care_assistant_qa.pages.SignInPage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SignInPageUITest {
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
    fun checkPlaceholders() {
        SignInPage.checkAllPlaceholders()
    }

    @Test
    fun checkLoginVisibility() {
        get(SignInLocators.LOGIN_INPUT).shouldBe(Condition.visible)
    }

    @Test
    fun checkPassVisibility() {
        get(SignInLocators.PASS_INPUT).shouldBe(Condition.visible)
    }

    @Test
    fun checkForgotPassVisibility() {
        get(SignInLocators.FORGOT_PASSWORD_BUTTON).shouldBe(Condition.visible)
    }

    @Test
    fun checkSignInButtonVisibility() {
        get(SignInLocators.SIGN_IN_BUTTON).shouldBe(Condition.visible)
    }

    @Test
    fun checkGoogleButtonVisibility() {
        all(SignInLocators.GOOGLE_BUTTON)[0].shouldBe(Condition.visible)
    }

    @Test
    fun checkYandexButtonVisibility() {
        all(SignInLocators.YANDEX_BUTTON)[1].shouldBe(Condition.visible)
    }

    @Test
    fun checkVisibilityOfEnteredTextLogin() {
        SignInPage.fillLoginInput("123@mail.ru")
        Assertions.assertEquals("123@mail.ru", get(SignInLocators.LOGIN_INPUT).`val`())
    }

    @Test
    fun checkVisibilityOfEnteredTextPass() {
        SignInPage.fillPassInput("pass")
        Assertions.assertEquals("pass", get(SignInLocators.PASS_INPUT).`val`())
    }

    @Test
    fun checkChangingOpacityOfConfirmSignInButtonWhileHovering() {
        get(SignInLocators.SIGN_IN_BUTTON).hover()
        SignInPage.checkOpacityOfSubmitButton()
    }

    @Test
    fun checkSignInSwitchButtonVisibility() {
        get(LoginPageLocators.SIGN_IN_PAGE).shouldBe(Condition.visible)
    }

    @Test
    fun checkChangingBorderOfSignInSwitchButton() {
        get(LoginPageLocators.SIGN_UP_PAGE).hover()
        SignInPage.checkBorderOfSignInSwitchButton()
    }

    @Test
    fun checkSwitchButtonText() {
        LoginPage.checkSelectedPage("singIn")
    }
}
