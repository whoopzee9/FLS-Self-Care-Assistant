package selfcareassistant.service

import selfcareassistant.entity.EmotionNameEntity
import java.util.*

interface EmotionsNameService {
    fun addEmotionsName(emotionNameEntity: EmotionNameEntity): UUID
    fun getAllEmotionsNames(): Iterable<EmotionNameEntity>
}
