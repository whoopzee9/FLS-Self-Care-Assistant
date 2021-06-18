package selfcareassistant.api.v1.dto.util

import selfcareassistant.api.v1.dto.RoleDto
import selfcareassistant.api.v1.dto.UserDto
import selfcareassistant.entity.RoleEntity
import selfcareassistant.entity.UserEntity

class MappingRoleUtils {
    fun mapToRoleDto(entity: RoleEntity): RoleDto {
        val role = RoleDto(entity.name)
        role.id = entity.id
        return role
    }

    fun mapToRoleEntity(dto: RoleDto): RoleEntity {
        val role = RoleEntity(dto.name)
        role.id = dto.id
        return role
    }
}