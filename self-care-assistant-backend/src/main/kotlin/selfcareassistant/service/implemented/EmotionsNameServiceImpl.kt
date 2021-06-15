package selfcareassistant.service.implemented

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import selfcareassistant.model.EmotionsName
import selfcareassistant.repository.EmotionsNameRepo
import selfcareassistant.service.EmotionsNameService
import java.util.*

@Service
class EmotionsNameServiceImpl: EmotionsNameService {

    @Autowired
    lateinit var emotionsNameRepo: EmotionsNameRepo

    override fun getAllEmotionsNames(): Iterable<EmotionsName> {
        return emotionsNameRepo.findAll()
    }

    override fun addEmotionsName(emotionsName: EmotionsName): UUID {
        return emotionsNameRepo.save(emotionsName).id
    }
}
