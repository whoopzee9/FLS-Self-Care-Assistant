package selfcareassistant.api.v2.dto

import java.util.*

class EmotionFilterDto {
    var lhsDate: Date = Date()

    var rhsDate: Date = Date()

    var emotionNames: List<EmotionNameFilterDto> = ArrayList()
}