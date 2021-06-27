package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*
import javax.validation.constraints.NotEmpty

@Schema
data class EmotionNameDto(
    var id: UUID? = null,

    @Schema(
          description = "Name of emotion",
          example = "гнев"
    )
    @NotEmpty
    var name: String
)