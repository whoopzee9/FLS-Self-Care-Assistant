package selfcareassistant.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import selfcareassistant.entity.EmotionNameEntity
import java.util.*

@Repository
interface EmotionsNameRepo: CrudRepository<EmotionNameEntity, UUID> {

}
