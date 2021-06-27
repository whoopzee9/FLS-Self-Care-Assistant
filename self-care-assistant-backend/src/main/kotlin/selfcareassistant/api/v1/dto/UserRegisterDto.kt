package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.stereotype.Component
import java.util.UUID
import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty

@Component
@Schema
class UserRegisterDto {
    @Schema(
            description = "Name",
            example = "Иванов Петр"
    )
    @NotBlank(message = "Name should not be empty")
    var name: String = ""

    @Schema(
            description = "Email",
            example = "ivanov@mail.ru"
    )
    @NotBlank(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    var email: String = ""

    @Schema(
            description = "Password"
    )
    @NotBlank(message = "Password should not be empty")
    var password: String = ""

    var roles: List<RoleDto> = ArrayList()
}