package selfcareassistant.api.v1.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.stereotype.Component
import java.util.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Component
@Schema
class EmotionFilterDto {
    @Schema(
            description = "left parameter of date",
            example = "2022-11-12 12:00:00"
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "LhsDate should not be null")
    lateinit var lhsDate: Date

    @Schema(
            description = "right parameter of date",
            example = "2022-12-12 12:00:00"
    )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "RhsDate should not be null")
    lateinit var rhsDate: Date

    @Valid
    var emotionNames: List<EmotionNameFilterDto> = ArrayList()
}