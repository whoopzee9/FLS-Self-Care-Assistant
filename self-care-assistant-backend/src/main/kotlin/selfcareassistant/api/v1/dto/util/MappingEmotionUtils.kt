package selfcareassistant.api.v1.dto.util

import selfcareassistant.api.v1.dto.EmotionDto
import selfcareassistant.api.v1.dto.EmotionNameDto
import selfcareassistant.api.v1.dto.UserDto
import selfcareassistant.entity.EmotionEntity
import selfcareassistant.entity.EmotionNameEntity
import selfcareassistant.entity.RoleEntity
import selfcareassistant.entity.UserEntity
import java.util.stream.Collectors

class MappingEmotionUtils {
    fun mapToEmotionDto(entity: EmotionEntity): EmotionDto {
        val emotion = EmotionDto()
        emotion.id = entity.id
        emotion.name = entity.name
        emotion.create_date = entity.create_date
        emotion.rate = entity.rate

        emotion.emotionName = EmotionNameDto(entity.emotionName?.id, entity.emotionName!!.name)
        return emotion
    }

    fun mapToEmotionEntity(dto: EmotionDto): EmotionEntity {
        val emotion = EmotionEntity()
        emotion.id = dto.id
        emotion.name = dto.name
        emotion.create_date = dto.create_date
        emotion.rate = dto.rate

        emotion.emotionName = EmotionNameEntity(dto.emotionName?.id, dto.emotionName!!.name)

        return emotion
    }
}