package selfcareassistant.api.v2.dto.util

import selfcareassistant.api.v2.dto.EmotionDto
import selfcareassistant.api.v2.dto.EmotionNameDto
import selfcareassistant.entity.EmotionEntity
import selfcareassistant.entity.EmotionNameEntity

class MappingEmotionUtils {
    fun mapToEmotionDto(entity: EmotionEntity): EmotionDto {
        val emotion = EmotionDto()
        emotion.id = entity.id
        emotion.createDate = entity.createDate
        emotion.intensity = entity.intensity

        emotion.emotionName = EmotionNameDto(entity.emotionName?.id, entity.emotionName!!.name)
        return emotion
    }

    fun mapToEmotionEntity(dto: EmotionDto): EmotionEntity {
        val emotion = EmotionEntity()
        emotion.id = dto.id
        emotion.createDate = dto.createDate
        emotion.intensity = dto.intensity

        emotion.emotionName = EmotionNameEntity(dto.emotionName?.id, dto.emotionName!!.name)

        return emotion
    }
}