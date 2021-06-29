package selfcareassistant.service

import selfcareassistant.entity.EmotionEntity
import selfcareassistant.entity.RoleEntity
import java.util.*

interface RoleService {
    fun addRole(roleEntity: RoleEntity): UUID
    fun getAllRoles(): Iterable<RoleEntity>
    fun deleteRole(id: UUID): Boolean
    fun changeRole(roleEntity: RoleEntity): Boolean
}
