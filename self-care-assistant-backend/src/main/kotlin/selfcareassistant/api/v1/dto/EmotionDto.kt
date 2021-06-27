package selfcareassistant.api.v1.dto

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*
import javax.validation.constraints.*

@Schema
data class EmotionDto(
    var id: UUID? = null,

    @Schema(
            example = "2020-02-17 00:00:00",
            name = "createDate"
    )
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @NotEmpty
    @Past
    var createDate: Date,

    @Schema(
            example = "2",
            name = "intensity"
    )
    @NotNull
    @Min(0)
    @Max(10)
    var intensity: Byte,

    @NotNull
    var emotionName: EmotionNameDto
)