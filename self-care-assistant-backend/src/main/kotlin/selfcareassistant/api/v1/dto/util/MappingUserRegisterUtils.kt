package selfcareassistant.api.v1.dto.util

import selfcareassistant.api.v1.dto.RoleDto
import selfcareassistant.api.v1.dto.UserRegisterDto
import selfcareassistant.entity.RoleEntity
import selfcareassistant.entity.UserEntity
import java.util.stream.Collectors

class MappingUserRegisterUtils {
    fun mapToUserRegisterDto(entity: UserEntity): UserRegisterDto {
        val user = UserRegisterDto()
        user.name = entity.name
        user.email = entity.email
        user.password = entity.password
        user.roles = entity.roles.stream()
                .map{ RoleDto(it.id, it.name) }
                .collect(Collectors.toList())
        return user
    }

    fun mapToUserEntity(registerDto: UserRegisterDto): UserEntity {
        val user = UserEntity()
        user.name = registerDto.name
        user.email = registerDto.email
        user.password = registerDto.password
        user.roles = registerDto.roles.stream()
                .map{ RoleEntity(it.id, it.name) }
                .collect(Collectors.toList())
        return user
    }
}