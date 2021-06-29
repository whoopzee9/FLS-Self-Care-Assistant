package com.fls.self_care_assistant.self_care_assistant_qa

import org.junit.platform.runner.JUnitPlatform
import org.junit.platform.suite.api.SelectPackages
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
@SelectPackages("com.fls.self_care_assistant.self_care_assistant_qa.ui_tests")
class UITestSuite {
}
