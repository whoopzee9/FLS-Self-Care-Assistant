package selfcareassistant.repository

import org.springframework.data.repository.CrudRepository
import selfcareassistant.model.Role
import java.util.*

interface RoleRepo : CrudRepository<Role, UUID> {
}