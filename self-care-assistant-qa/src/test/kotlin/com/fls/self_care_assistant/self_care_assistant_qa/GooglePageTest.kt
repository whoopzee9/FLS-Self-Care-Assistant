package com.fls.self_care_assistant.self_care_assistant_qa

import com.codeborne.selenide.CollectionCondition.sizeGreaterThan
import com.codeborne.selenide.Selenide.*
import com.fls.self_care_assistant.self_care_assistant_qa.Extensions.Companion.all
import com.fls.self_care_assistant.self_care_assistant_qa.Extensions.Companion.get
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class GooglePageTest {
    companion object {
        @BeforeAll
        @JvmStatic
        fun setup() {
            open("https://google.com")
        }
    }

    @Test
    fun user_can_search() {
        get("[name=q]").setValue("selenide").pressEnter()
        all("#res .g").shouldHave(sizeGreaterThan(5))
    }
}
