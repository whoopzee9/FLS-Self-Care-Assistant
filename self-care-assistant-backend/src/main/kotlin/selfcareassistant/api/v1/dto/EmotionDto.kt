package selfcareassistant.api.v1.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import java.util.*
import javax.validation.constraints.*

@Component
@Schema
class EmotionDto {
    var id: UUID? = null

    @Schema(
            example = "2020-02-17 00:00:00",
            name = "createDate"
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "Date should not be null")
    @Past(message = "Date should not be more than the current one")
    lateinit var createDate: Date

    @Schema(
            example = "2",
            name = "intensity"
    )
    @NotNull(message = "Intensity should not be null")
    @Min(0, message = "Intensity should not be less than 0")
    @Max(10, message = "Intensity should not be more than 10")
    var intensity: Byte = 0

    @NotNull(message = "Emotion name should not be null")
    lateinit var emotionName: EmotionNameDto
}