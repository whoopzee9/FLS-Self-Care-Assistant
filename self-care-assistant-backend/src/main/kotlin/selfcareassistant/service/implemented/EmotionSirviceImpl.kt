package selfcareassistant.service.implemented

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import selfcareassistant.model.Emotion
import selfcareassistant.repository.EmotionRepo
import selfcareassistant.service.EmotionService
import java.util.*

@Service
class EmotionSirviceImpl : EmotionService {
    @Autowired
    lateinit var emotionRepo: EmotionRepo

    override fun addEmotion(emotion: Emotion): UUID {
        emotionRepo.save(emotion)
        return emotion.id
    }

    override fun getAllEmotions(): Iterable<Emotion> {
        return emotionRepo.findAll()
    }
}
