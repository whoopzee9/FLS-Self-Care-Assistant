package selfcareassistant.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import selfcareassistant.model.User
import java.util.*

@Repository
interface UserRepo : CrudRepository<User, UUID> {
    fun findByName(@Param("name") name: String): User
}