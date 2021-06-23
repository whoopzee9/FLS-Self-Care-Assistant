package selfcareassistant.service

import selfcareassistant.entity.EmotionNameEntity
import java.util.*

interface EmotionsNameService {
    fun addEmotionName(emotionNameEntity: EmotionNameEntity): UUID
    fun getAllEmotionNames(): Iterable<EmotionNameEntity>
    fun deleteEmotionName(id: UUID): Boolean
}
