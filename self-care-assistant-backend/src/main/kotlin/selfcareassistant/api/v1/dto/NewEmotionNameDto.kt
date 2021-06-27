package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.stereotype.Component
import java.util.*
import javax.validation.constraints.NotBlank

@Component
@Schema
class NewEmotionNameDto {
    var id: UUID? = null

    @Schema(
            description = "Name of emotion",
            example = "гнев"
    )
    @NotBlank(message = "Name should not be empty")
    var name: String = ""
}