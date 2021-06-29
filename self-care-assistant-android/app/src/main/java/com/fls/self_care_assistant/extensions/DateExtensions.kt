package com.fls.self_care_assistant.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.prettyFormat(): String {
    return SimpleDateFormat("dd.MM.yy hh:mm").format(this)
}

fun Date.requestFormat(): String {
    return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this)
}