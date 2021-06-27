package selfcareassistant.api.v1.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema
data class EmotionFilterDto(
    @Schema(
            description = "left parameter of date",
            example = "2022-11-12 12:00:00"
    )
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    var lhsDate: Date = Date(),

    @Schema(
            description = "right parameter of date",
            example = "2022-12-12 12:00:00"
    )
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    var rhsDate: Date = Date(),

    var emotionNames: List<EmotionNameFilterDto> = ArrayList()
)