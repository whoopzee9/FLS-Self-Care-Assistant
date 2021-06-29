package selfcareassistant.service

import selfcareassistant.api.v1.dto.EmotionNameFilterDto
import selfcareassistant.entity.EmotionEntity
import java.util.*
import javax.servlet.http.HttpServletRequest

interface EmotionService {
    fun addEmotion(request: HttpServletRequest, emotionEntity: EmotionEntity): UUID
    fun getEmotionsByDateAndEmotionNames(request: HttpServletRequest,
                             lhsDate: Date,
                             rhsDate: Date,
                             emotionNames: List<EmotionNameFilterDto>): Iterable<EmotionEntity>
    fun getAllEmotions(request: HttpServletRequest): Iterable<EmotionEntity>
    fun deleteEmotion(id: UUID): Boolean
    fun changeEmotion(emotionEntity: EmotionEntity): Boolean
}

