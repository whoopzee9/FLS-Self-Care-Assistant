package selfcareassistant.service

import selfcareassistant.entity.EmotionEntity
import selfcareassistant.entity.EmotionNameEntity
import java.util.*

interface EmotionNameService {
    fun addEmotionName(emotionNameEntity: EmotionNameEntity): UUID
    fun getAllEmotionNames(): Iterable<EmotionNameEntity>
    fun deleteEmotionName(id: UUID): Boolean
    fun changeEmotionName(emotionNameEntity: EmotionNameEntity): Boolean
}
