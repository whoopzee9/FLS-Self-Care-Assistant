package selfcareassistant.api.v1.dto.util

import selfcareassistant.api.v1.dto.NewEmotionDto
import selfcareassistant.api.v1.dto.EmotionNameDto
import selfcareassistant.entity.EmotionEntity
import selfcareassistant.entity.EmotionNameEntity

class MappingNewEmotionUtils {
    fun mapToEmotionDto(entity: EmotionEntity): NewEmotionDto {
        val emotion = NewEmotionDto()
        emotion.createDate = entity.createDate
        emotion.intensity = entity.intensity

        val emotionNameDto = EmotionNameDto()
        emotionNameDto.id = entity.emotionName?.id
        emotionNameDto.name = entity.emotionName!!.name

        emotion.emotionName = emotionNameDto
        return emotion
    }

    fun mapToEmotionEntity(dtoNew: NewEmotionDto): EmotionEntity {
        val emotion = EmotionEntity()
        emotion.createDate = dtoNew.createDate
        emotion.intensity = dtoNew.intensity

        emotion.emotionName = EmotionNameEntity(dtoNew.emotionName.id, dtoNew.emotionName.name)

        return emotion
    }
}