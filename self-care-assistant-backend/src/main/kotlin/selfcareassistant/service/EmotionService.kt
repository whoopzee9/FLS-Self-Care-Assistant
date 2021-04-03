package selfcareassistant.service

import selfcareassistant.model.Emotion
import java.util.*

interface EmotionService {
    fun addEmotion(emotion: Emotion): UUID
    fun getAllEmotions(): Iterable<Emotion>
}