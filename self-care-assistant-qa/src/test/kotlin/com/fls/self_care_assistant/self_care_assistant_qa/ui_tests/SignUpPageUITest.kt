package com.fls.self_care_assistant.self_care_assistant_qa.ui_tests

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.fls.self_care_assistant.self_care_assistant_qa.LoginPageLocators
import com.fls.self_care_assistant.self_care_assistant_qa.SignUpLocators
import com.fls.self_care_assistant.self_care_assistant_qa.get
import com.fls.self_care_assistant.self_care_assistant_qa.pages.LoginPage
import com.fls.self_care_assistant.self_care_assistant_qa.pages.SignUpPage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SignUpPageUITest {
    @BeforeEach
    fun setup() {
        Selenide.open("http://localhost:3000/login")
        get(LoginPageLocators.SIGN_UP_PAGE).click()
    }

    @AfterEach
    fun closeBrowser() {
        Selenide.closeWebDriver()
    }

    @Test
    fun checkMainWindowVisibility() {
        get(LoginPageLocators.MAIN_WINDOW).shouldBe(Condition.visible)
    }

    @Test
    fun checkSignUpSwitchButtonVisibility() {
        get(LoginPageLocators.SIGN_UP_PAGE).shouldBe(Condition.visible)
    }

    @Test
    fun checkChangingBorderOfSignInSwitchButton() {
        get(LoginPageLocators.SIGN_IN_PAGE).hover()
        SignUpPage.checkBorderOfSignInSwitchButton()
    }

    @Test
    fun checkNameInputVisibility() {
        get(SignUpLocators.NAME_INPUT).shouldBe(Condition.visible)
    }

    @Test
    fun checkEmailInputVisibility() {
        get(SignUpLocators.EMAIL_INPUT).shouldBe(Condition.visible)
    }

    @Test
    fun checkPassInputVisibility() {
        get(SignUpLocators.PASS_INPUT).shouldBe(Condition.visible)
    }

    @Test
    fun checkConfirmPassInputVisibility() {
        get(SignUpLocators.CONFIRM_PASS_INPUT).shouldBe(Condition.visible)
    }

    @Test
    fun checkAcceptPrivacyPolicyVisibility() {
        get(SignUpLocators.PRIVACY_POLICY).shouldBe(Condition.visible)
    }

    @Test
    fun checkConfirmSignUpButtonVisibility() {
        get(SignUpLocators.CONFIRM_SIGN_UP_BUTTON).shouldBe(Condition.visible)
    }

    @Test
    fun acceptPrivacyPolicyShouldBeUncheckedBYDefault() {
        get(SignUpLocators.PRIVACY_POLICY).shouldNotBe(Condition.checked)
    }

    @Test
    fun checkPrivacyPolicyChecked() {
        SignUpPage.acceptPrivacyPolicy()
        get(SignUpLocators.PRIVACY_POLICY).shouldBe(Condition.checked)
    }

    @Test
    fun checkChangingOpacityOfConfirmSignUpButtonWhileHovering() {
        get(SignUpLocators.CONFIRM_SIGN_UP_BUTTON).hover()
        SignUpPage.checkOpacityOfSubmitButton()
    }

    @Test
    fun checkSwitchButtonText() {
        LoginPage.checkSelectedPage("singUp")
    }

    @Test
    fun checkPlaceholders() {
        SignUpPage.checkAllPlaceholders()
    }

    @Test
    fun checkVisibilityOfEnteredTextName() {
        SignUpPage.fillNameInput("123")
        Assertions.assertEquals("123", get(SignUpLocators.NAME_INPUT).`val`())
    }

    @Test
    fun checkVisibilityOfEnteredTextEmail() {
        SignUpPage.fillEmailInput("123@mail.ru")
        Assertions.assertEquals("123@mail.ru", get(SignUpLocators.EMAIL_INPUT).`val`())
    }

    @Test
    fun checkVisibilityOfEnteredTextPass() {
        SignUpPage.fillPassInput("pass")
        Assertions.assertEquals("pass", get(SignUpLocators.PASS_INPUT).`val`())
    }

    @Test
    fun checkVisibilityOfEnteredTextConfirmPass() {
        SignUpPage.fillConfirmPassInput("pass")
        Assertions.assertEquals("pass", get(SignUpLocators.CONFIRM_PASS_INPUT).`val`())
    }
}
