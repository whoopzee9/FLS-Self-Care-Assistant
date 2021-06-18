package selfcareassistant.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import selfcareassistant.entity.EmotionEntity
import selfcareassistant.entity.UserEntity
import java.util.*

@Repository
interface EmotionRepo: CrudRepository<EmotionEntity, UUID> {
    fun findAllByUser(userEntity: UserEntity): Iterable<EmotionEntity>
}
