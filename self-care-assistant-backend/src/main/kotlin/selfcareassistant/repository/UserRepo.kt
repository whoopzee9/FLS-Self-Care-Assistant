package selfcareassistant.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import selfcareassistant.entity.UserEntity
import java.util.*

@Repository
interface UserRepo: CrudRepository<UserEntity, UUID> {
    fun findByEmail(email: String): Optional<UserEntity>
}
