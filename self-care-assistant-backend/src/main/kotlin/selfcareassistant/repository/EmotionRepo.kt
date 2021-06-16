package selfcareassistant.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import selfcareassistant.model.Emotion
import selfcareassistant.model.User
import java.util.*

@Repository
interface EmotionRepo : CrudRepository<Emotion, UUID> {
    fun findAllByUser(user: User): Iterable<Emotion>
}
