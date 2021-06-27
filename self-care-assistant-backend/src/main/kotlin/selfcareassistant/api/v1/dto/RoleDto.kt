package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema
class RoleDto(
        var id: UUID? = null,
        @Schema(
                description = "Name of role",
                example = "user"
        )
        var name: String
)
