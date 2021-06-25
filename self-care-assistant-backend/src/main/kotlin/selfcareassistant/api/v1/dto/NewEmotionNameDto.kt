package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema
data class NewEmotionNameDto(
    var id: UUID,
    @Schema(
          description = "Name of emotion",
          example = "гнев"
    )
    var name: String
)