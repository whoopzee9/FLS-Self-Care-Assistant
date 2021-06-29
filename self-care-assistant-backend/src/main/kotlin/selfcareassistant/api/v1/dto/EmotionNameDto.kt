package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.stereotype.Component
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Component
@Schema
class EmotionNameDto {
    @NotNull(message = "Id should not be null")
    var id: UUID? = null

    @Schema(
            description = "Name of emotion",
            example = "гнев"
    )
    @NotBlank(message = "Name should not be empty")
    lateinit var name: String
}