package selfcareassistant.api.v1.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema
class EmotionDto {
    var id: UUID? = null

    @Schema(
            description = "date of creation",
            example = "2022-12-12 12:00:00"
    )
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    var createDate: Date = Date()

    @Schema(
            description = "intensity of emotion",
            example = "2"
    )
    var intensity: Byte = 0

    var emotionName: EmotionNameDto? = null
}