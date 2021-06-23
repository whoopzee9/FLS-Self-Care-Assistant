package selfcareassistant.service.implemented

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import selfcareassistant.entity.EmotionNameEntity
import selfcareassistant.repository.EmotionsNameRepo
import selfcareassistant.service.EmotionsNameService
import java.util.*

@Service
class EmotionsNameServiceImpl: EmotionsNameService {

    @Autowired
    lateinit var emotionsNameRepo: EmotionsNameRepo

    override fun getAllEmotionsNames(): Iterable<EmotionNameEntity> {
        return emotionsNameRepo.findAll()
    }

    override fun addEmotionsName(emotionNameEntity: EmotionNameEntity): UUID {
        return emotionsNameRepo.save(emotionNameEntity).id!!
    }
}
