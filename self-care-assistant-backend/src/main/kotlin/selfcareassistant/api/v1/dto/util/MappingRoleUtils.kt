package selfcareassistant.api.v1.dto.util

import selfcareassistant.api.v1.dto.RoleDto
import selfcareassistant.api.v1.dto.UserRegisterDto
import selfcareassistant.entity.RoleEntity
import selfcareassistant.entity.UserEntity
import java.util.stream.Collectors

class MappingRoleUtils {
    fun mapToRoleDto(entity: RoleEntity): RoleDto {
        val role = RoleDto()
        role.id = entity.id
        role.name = entity.name

        return role
    }

    fun mapToRoleEntity(dto: RoleDto): RoleEntity {
        val role = RoleEntity(dto.id, dto.name)

        return role
    }
}