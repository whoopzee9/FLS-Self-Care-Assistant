package selfcareassistant.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import selfcareassistant.model.EmotionsName
import java.util.*

@Repository
interface EmotionsNameRepo : CrudRepository<EmotionsName, UUID> {

}
