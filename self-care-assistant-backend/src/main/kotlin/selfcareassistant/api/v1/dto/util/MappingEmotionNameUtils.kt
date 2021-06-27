package selfcareassistant.api.v1.dto.util

import selfcareassistant.api.v1.dto.EmotionNameDto
import selfcareassistant.api.v1.dto.RoleDto
import selfcareassistant.entity.EmotionNameEntity
import selfcareassistant.entity.RoleEntity

class MappingEmotionNameUtils {
    fun mapToEmotionNameDto(entity: EmotionNameEntity): EmotionNameDto {
        val emotionName = EmotionNameDto()
        emotionName.id = entity.id
        emotionName.name = entity.name

        return emotionName
    }

    fun mapToEmotionNameEntity(dto: EmotionNameDto): EmotionNameEntity {
        val emotionName = EmotionNameEntity(dto.id, dto.name)

        return emotionName
    }
}