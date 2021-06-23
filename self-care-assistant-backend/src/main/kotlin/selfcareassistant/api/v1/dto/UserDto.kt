package selfcareassistant.api.v1.dto

import java.util.UUID
import javax.validation.constraints.Email

class UserDto {
    var id: UUID? = null

    var name: String = ""

    @Email(message = "Email should be valid")
    var email: String = ""

    var password: String = ""

    var roles: List<RoleDto> = ArrayList()
}