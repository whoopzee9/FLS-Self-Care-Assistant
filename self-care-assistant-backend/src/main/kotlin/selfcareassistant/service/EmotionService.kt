package selfcareassistant.service

import selfcareassistant.model.Emotion
import java.util.*
import javax.servlet.http.HttpServletRequest

interface EmotionService {
    fun addEmotion(request: HttpServletRequest, emotion: Emotion): UUID
    fun getAllEmotions(request: HttpServletRequest): Iterable<Emotion>
}

