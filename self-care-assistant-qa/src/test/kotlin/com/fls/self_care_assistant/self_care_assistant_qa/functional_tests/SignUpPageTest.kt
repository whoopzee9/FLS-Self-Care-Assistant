package com.fls.self_care_assistant.self_care_assistant_qa.functional_tests

import com.codeborne.selenide.Selenide
import com.codeborne.selenide.WebDriverRunner.getWebDriver
import com.fls.self_care_assistant.self_care_assistant_qa.LoginPageLocators
import com.fls.self_care_assistant.self_care_assistant_qa.get
import com.fls.self_care_assistant.self_care_assistant_qa.pages.LoginPage
import com.fls.self_care_assistant.self_care_assistant_qa.pages.SignUpPage
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class SignUpPageTest {
    @BeforeEach
    fun setup() {
        Selenide.open("http://localhost:3000/login")
        get(LoginPageLocators.SIGN_UP_PAGE).click()
    }

    @AfterEach
    fun closeBrowser()
    {
        Selenide.closeWebDriver()
    }

    @Test
    fun registerUserWithCorrectData() {
        SignUpPage.fillAllInputs("name", "email@mail.ru", "pass", "pass")
        SignUpPage.acceptPrivacyPolicy()
        SignUpPage.confirmRegister()
        SignUpPage.checkCorrectRegisterAlert()
        WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.alertIsPresent()).accept()
        LoginPage.checkSelectedPage("singIn")
    }

    @Test
    fun registerUserWithoutPrivacyPolicy() {
        SignUpPage.fillAllInputs("name", "email@mail.ru", "pass", "pass")
        SignUpPage.confirmRegister()
        SignUpPage.checkUnselectedPrivacyPolicyAlert()
    }

    @Test
    fun registerUserWithDifferentPass() {
        SignUpPage.fillAllInputs("name", "email@mail.ru", "pass", "12321")
        SignUpPage.acceptPrivacyPolicy()
        SignUpPage.confirmRegister()
        SignUpPage.checkDifferentPassAlert()
    }

    @Test
    fun registerUserWithDifferentPassAndClearingPassInputs() {
        SignUpPage.fillAllInputs("name", "email@mail.ru", "pass", "12321")
        SignUpPage.acceptPrivacyPolicy()
        SignUpPage.confirmRegister()
        SignUpPage.checkDifferentPassAlert()
        WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.alertIsPresent()).accept()
        SignUpPage.checkEmptyPassInputs()
    }

    /**
     * Пока что падает
     * Нет обработки невалидной почты
     */
    @Test
    fun registerUserWithIncorrectEmail() {
        SignUpPage.fillAllInputs("name", "emailmail.ru", "pass", "12321")
        SignUpPage.acceptPrivacyPolicy()
        SignUpPage.confirmRegister()
        SignUpPage.checkIncorrectEmailAlert()
    }

    /**
     * Пока что падает
     * Нет уведомления о невведённых данных
     */
    @Test
    fun registerUserWithEmptyData() {
        SignUpPage.acceptPrivacyPolicy()
        SignUpPage.confirmRegister()
        SignUpPage.checkEmptyDataAlert()
    }

    /**
     * Пока что падает
     * Нет уведомления о пробелах/неправильных данных
     */
    @Test
    fun registerUserWithAllSpaces() {
        SignUpPage.fillAllInputs(" ", " ", " ", " ")
        SignUpPage.acceptPrivacyPolicy()
        SignUpPage.confirmRegister()
        SignUpPage.checkEmptyDataAlert()
    }

    /**
     * Пока что падает
     * По идее нельзя вводить данные, обрамлённые пробелами
     */
    @Test
    fun registerUserDataWithSpaces() {
        SignUpPage.fillAllInputs(" 123 ", " email@mail.ru ", " 123 ", " 123 ")
        SignUpPage.acceptPrivacyPolicy()
        SignUpPage.confirmRegister()
        SignUpPage.checkEmptyDataAlert()
    }

    /**
     * Пока что падает
     * Нет ограничения на макс. длину символов
     */
    @Test
    fun registerUserWithLongData() {
        SignUpPage.fillAllInputs("11213214124321412412421412421421412412421412",
            "email@mail.ru", "123", "123")
        SignUpPage.acceptPrivacyPolicy()
        SignUpPage.confirmRegister()
        SignUpPage.checkWrongDataLengthAlert()
    }

    /**
     * Пока что падает
     * Нет уведомления о запрете использования спецсимволов
     */
    @Test
    fun registerUserWithSpecialSymbols() {
        SignUpPage.fillAllInputs("!@#\$%^&*()_+\n", "email@mail.ru", "123", "123")
        SignUpPage.acceptPrivacyPolicy()
        SignUpPage.confirmRegister()
        SignUpPage.checkUsingSpecialSymbolsAlert()
    }

    /**
     * Пока что падает
     * Нет связи с беком/бд, поэтому пока что можно регистрировать с одними и теми же данными
     */
    @Test
    fun registerTwoUsersWithSampleData() {
        SignUpPage.fillAllInputs("name", "email@mail.ru", "pass", "pass")
        SignUpPage.acceptPrivacyPolicy()
        SignUpPage.confirmRegister()
        SignUpPage.checkCorrectRegisterAlert()
        get(LoginPageLocators.SIGN_UP_PAGE).click()
        SignUpPage.fillAllInputs("name", "email@mail.ru", "pass", "pass")
        SignUpPage.confirmRegister()
        SignUpPage.checkTwoUsersWithSampleDataAlert()
    }
}
