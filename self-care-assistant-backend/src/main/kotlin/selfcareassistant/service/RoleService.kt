package selfcareassistant.service

import selfcareassistant.model.Role
import java.util.*

interface RoleService {
    fun addRole(role: Role): UUID
    fun getAllRoles(): Iterable<Role>
}
