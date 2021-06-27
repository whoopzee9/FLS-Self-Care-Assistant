package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema
class UserCredentials(
    @Schema(
            description = "Email",
            example = "ivanov@mail.ru"
    )
    var email: String,

    @Schema(
            description = "Password"
    )
    var password: String = ""
)