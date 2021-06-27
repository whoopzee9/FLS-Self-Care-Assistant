package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.stereotype.Component
import java.util.*
import javax.validation.constraints.NotBlank

@Component
@Schema
class RoleDto {
        var id: UUID? = null

        @Schema(
                description = "Name of role",
                example = "user"
        )
        @NotBlank(message = "Name should not be empty")
        lateinit var name: String
}
