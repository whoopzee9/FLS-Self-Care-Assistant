package selfcareassistant.api.v1.dto.util

import selfcareassistant.api.v1.dto.RoleDto
import selfcareassistant.api.v1.dto.UserRegisterDto
import selfcareassistant.entity.RoleEntity
import selfcareassistant.entity.UserEntity
import java.util.stream.Collectors

class MappingUserRegisterUtils {
    fun mapToUserRegisterDto(entity: UserEntity): UserRegisterDto {

        val roles = entity.roles.stream()
                .map{ RoleDto(it.id, it.name) }
                .collect(Collectors.toList())
        return UserRegisterDto(entity.name, entity.email, entity.password, roles)
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