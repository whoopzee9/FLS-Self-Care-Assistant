package selfcareassistant.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import selfcareassistant.entity.EmotionEntity
import selfcareassistant.entity.EmotionNameEntity
import java.util.*

@Repository
interface EmotionNameRepo: CrudRepository<EmotionNameEntity, UUID> {
}
