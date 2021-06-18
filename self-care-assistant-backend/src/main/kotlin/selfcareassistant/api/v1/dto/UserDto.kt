package selfcareassistant.api.v1.dto

import selfcareassistant.entity.RoleEntity
import java.util.UUID
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

class UserDto {
    var id: UUID? = null

    var name: String = ""

    var email: String = ""

    var password: String = ""

    var roles: List<RoleDto> = ArrayList()
}