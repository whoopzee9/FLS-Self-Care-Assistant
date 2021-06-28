package com.fls.self_care_assistant.self_care_assistant_qa.pages

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide
import com.fls.self_care_assistant.self_care_assistant_qa.SignUpLocators
import com.fls.self_care_assistant.self_care_assistant_qa.get
import org.junit.jupiter.api.Assertions

class SignUpPage {
    companion object {
        private fun fillNameInput(name: String) {
            get(SignUpLocators.NAME_INPUT).value = name
        }

        private fun fillEmailInput(email: String) {
            get(SignUpLocators.EMAIL_INPUT).value = email
        }

        private fun fillPassInput(pass: String) {
            get(SignUpLocators.PASS_INPUT).value = pass
        }

        private fun fillConfirmPassInput(confirmPass: String) {
            get(SignUpLocators.CONFIRM_PASS_INPUT).value = confirmPass
        }

        fun fillAllInputs(name: String, email: String, pass: String, confirmPass: String) {
            fillNameInput(name)
            fillEmailInput(email)
            fillPassInput(pass)
            fillConfirmPassInput(confirmPass)
        }

        fun acceptPrivacyPolicy() {
            get(SignUpLocators.PRIVACY_POLICY).click()
        }

        fun confirmRegister() {
            get(SignUpLocators.CONFIRM_SIGN_UP_BUTTON).click()
        }

        fun checkEmptyPassInputs()
        {
            get(SignUpLocators.PASS_INPUT).shouldBe(Condition.empty)
            get(SignUpLocators.CONFIRM_PASS_INPUT).shouldBe(Condition.empty)
        }

        fun checkCorrectRegisterAlert() {
            Assertions.assertEquals("Вы успешно зарегистрировались", Selenide.switchTo().alert().text)
        }

        fun checkUnselectedPrivacyPolicyAlert() {
            Assertions.assertEquals("Поставьте галочку!!!!", Selenide.switchTo().alert().text)
        }

        fun checkDifferentPassAlert() {
            Assertions.assertEquals("У вас не совпадают пароли", Selenide.switchTo().alert().text)
        }

        fun checkEmptyDataAlert() {
            Assertions.assertEquals("Вы не ввели данные", Selenide.switchTo().alert().text)
        }

        fun checkWrongDataLengthAlert() {
            Assertions.assertEquals("Некорректная длина введенных символов", Selenide.switchTo().alert().text)
        }

        fun checkUsingSpecialSymbolsAlert() {
            Assertions.assertEquals("Нельзя использовать спецсимволы", Selenide.switchTo().alert().text)
        }

        fun checkTwoUsersWithSampleDataAlert() {
            Assertions.assertEquals("Пользователь с данной почтой уже существует", Selenide.switchTo().alert().text)
        }

        fun checkIncorrectEmailAlert() {
            Assertions.assertEquals("Неверно введена почта", Selenide.switchTo().alert().text)
        }
    }
}
