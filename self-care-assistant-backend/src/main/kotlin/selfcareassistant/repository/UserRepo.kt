package selfcareassistant.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import selfcareassistant.model.User
import java.util.*

interface UserRepo : CrudRepository<User, UUID> {
    fun findByName(@Param("name") name: String): User
}
