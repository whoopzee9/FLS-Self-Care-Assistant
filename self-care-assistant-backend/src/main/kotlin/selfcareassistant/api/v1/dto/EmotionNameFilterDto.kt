package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.Valid
import javax.validation.constraints.*

@Component
@Schema
class EmotionNameFilterDto {
    @NotNull(message = "Emotion name should not be null")
    var emotionName: EmotionNameDto? = null

    @Schema(
            description = "left parameter of intensity",
            example = "2"
    )
    @NotNull(message = "LhsIntensity should not be null")
    @Min(0, message = "LhsIntensity should not be less than 0")
    @Max(10, message = "LhsIntensity should not be more than 10")
    var lhsIntensity: Byte = 0

    @Schema(
            description = "right parameter of intensity",
            example = "7"
    )
    @NotNull(message = "RhsIntensity should not be null")
    @Min(0, message = "RhsIntensity should not be less than 0")
    @Max(10, message = "RhsIntensity should not be more than 10")
    var rhsIntensity: Byte = 0
}