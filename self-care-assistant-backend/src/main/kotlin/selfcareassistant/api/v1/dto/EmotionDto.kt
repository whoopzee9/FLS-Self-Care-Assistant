package selfcareassistant.api.v1.dto

import java.util.*

class EmotionDto {
    var id: UUID? = null

    var name: String = ""

    var create_date: Date = Date()

    var rate: Byte = 0

    var emotionName: EmotionNameDto? = null
}