package selfcareassistant.repository

import org.springframework.data.repository.CrudRepository
import selfcareassistant.entity.RoleEntity
import java.util.*

interface RoleRepo: CrudRepository<RoleEntity, UUID> {
}