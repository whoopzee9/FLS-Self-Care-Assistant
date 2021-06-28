package com.fls.self_care_assistant.self_care_assistant_qa

import com.codeborne.selenide.ElementsCollection
import com.codeborne.selenide.Selenide.`$$`
import com.codeborne.selenide.Selenide.`$`
import com.codeborne.selenide.SelenideElement

fun get(selector: String) : SelenideElement {
    return `$`(selector)
}

fun all(selector: String) : ElementsCollection {
    return `$$`(selector)
}
