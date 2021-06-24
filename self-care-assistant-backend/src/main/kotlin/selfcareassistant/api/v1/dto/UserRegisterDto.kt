package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID
import javax.validation.constraints.Email

@Schema
class UserRegisterDto {
    var name: String = ""

    @Schema(
            description = "Email",
            example = "ivanov@mail.ru"
    )
    var email: String = ""

    @Schema(
            description = "Password"
    )
    var password: String = ""

    var roles: List<RoleDto> = ArrayList()
}