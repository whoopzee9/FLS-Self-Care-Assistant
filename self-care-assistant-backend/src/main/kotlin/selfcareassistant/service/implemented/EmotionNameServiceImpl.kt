package selfcareassistant.service.implemented

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import selfcareassistant.entity.EmotionNameEntity
import selfcareassistant.repository.EmotionNameRepo
import selfcareassistant.service.EmotionsNameService
import java.util.*

@Service
class EmotionNameServiceImpl: EmotionsNameService {

    @Autowired
    lateinit var emotionNameRepo: EmotionNameRepo

    override fun getAllEmotionNames(): Iterable<EmotionNameEntity> {
        return emotionNameRepo.findAll()
    }

    override fun addEmotionName(emotionNameEntity: EmotionNameEntity): UUID {
        return emotionNameRepo.save(emotionNameEntity).id!!
    }

    override fun deleteEmotionName(id: UUID): Boolean {
        if(!emotionNameRepo.findById(id).isPresent()) {
            return false
        }

        emotionNameRepo.deleteById(id)
        return true
    }
}
