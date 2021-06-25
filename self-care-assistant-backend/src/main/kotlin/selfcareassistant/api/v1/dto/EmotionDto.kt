package selfcareassistant.api.v1.dto

import java.util.*

class EmotionDto {
    var id: UUID? = null

    var createDate: Date = Date()

    var intensity: Byte = 0

    var emotionName: EmotionNameDto? = null
}