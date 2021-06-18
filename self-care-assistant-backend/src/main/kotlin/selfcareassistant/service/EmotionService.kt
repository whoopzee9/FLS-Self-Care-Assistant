package selfcareassistant.service

import selfcareassistant.entity.EmotionEntity
import java.util.*
import javax.servlet.http.HttpServletRequest

interface EmotionService {
    fun addEmotion(request: HttpServletRequest, emotionEntity: EmotionEntity): UUID
    fun getAllEmotions(request: HttpServletRequest): Iterable<EmotionEntity>
}

