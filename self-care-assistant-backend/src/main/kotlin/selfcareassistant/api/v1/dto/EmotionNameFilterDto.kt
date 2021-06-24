package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema
class EmotionNameFilterDto {
    var emotionName: EmotionNameDto? = null

    @Schema(
            description = "left parameter of intensity",
            example = "2"
    )
    var lhsIntensity: Byte = 0

    @Schema(
            description = "right parameter of intensity",
            example = "7"
    )
    var rhsIntensity: Byte = 0
}