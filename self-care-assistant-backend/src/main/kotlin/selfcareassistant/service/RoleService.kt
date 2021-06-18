package selfcareassistant.service

import selfcareassistant.entity.RoleEntity
import java.util.*

interface RoleService {
    fun addRole(roleEntity: RoleEntity): UUID
    fun getAllRoles(): Iterable<RoleEntity>
}
