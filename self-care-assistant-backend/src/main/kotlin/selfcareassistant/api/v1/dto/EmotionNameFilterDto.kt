package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema
data class EmotionNameFilterDto(
    var emotionName: EmotionNameDto,

    @Schema(
            description = "left parameter of intensity",
            example = "2"
    )
    var lhsIntensity: Byte,

    @Schema(
            description = "right parameter of intensity",
            example = "7"
    )
    var rhsIntensity: Byte,
)