package selfcareassistant.api.v1.dto.util

import selfcareassistant.api.v1.dto.RoleDto
import selfcareassistant.api.v1.dto.UserDto
import selfcareassistant.entity.RoleEntity
import selfcareassistant.entity.UserEntity
import java.util.stream.Collectors

class MappingUserUtils {
    fun mapToUserDto(entity: UserEntity): UserDto {
        val user = UserDto()
        user.id = entity.id
        user.name = entity.name
        user.email = entity.email
        user.password = entity.password
        user.roles = entity.roles.stream()
                .map{ RoleDto(it.id, it.name) }
                .collect(Collectors.toList())
        return user
    }

    fun mapToUserEntity(dto: UserDto): UserEntity {
        val user = UserEntity()
        user.id = dto.id
        user.name = dto.name
        user.email = dto.email
        user.password = dto.password
        user.roles = dto.roles.stream()
                .map{ RoleEntity(it.id, it.name) }
                .collect(Collectors.toList())
        return user
    }
}