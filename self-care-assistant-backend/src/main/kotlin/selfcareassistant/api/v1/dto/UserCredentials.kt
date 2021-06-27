package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.stereotype.Component
import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

@Component
@Schema
class UserCredentials {
    @Schema(
            description = "Email",
            example = "ivanov@mail.ru"
    )
    @NotBlank(message = "Email should be valid")
    @Email(message = "Email should not be empty")
    var email: String = ""

    @Schema(
            description = "Password"
    )
    @NotBlank(message = "Password should not be empty")
    var password: String = ""
}