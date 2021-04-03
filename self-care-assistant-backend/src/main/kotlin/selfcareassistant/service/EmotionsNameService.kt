package selfcareassistant.service

import selfcareassistant.model.EmotionsName
import java.util.*

interface EmotionsNameService {
    fun addEmotionsName(emotionsName: EmotionsName): UUID
    fun getAllEmotionsNames(): Iterable<EmotionsName>
}