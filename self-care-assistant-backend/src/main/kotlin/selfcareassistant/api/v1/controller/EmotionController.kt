package selfcareassistant.api.v1.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import selfcareassistant.api.v1.dto.EmotionDto
import selfcareassistant.api.v1.dto.EmotionNameDto
import selfcareassistant.api.v1.dto.util.MappingEmotionUtils
import selfcareassistant.api.v1.dto.util.MappingRoleUtils
import selfcareassistant.entity.EmotionEntity
import selfcareassistant.entity.EmotionNameEntity
import selfcareassistant.service.EmotionService
import selfcareassistant.service.EmotionsNameService
import java.util.*
import java.util.stream.Collectors
import javax.servlet.http.HttpServletRequest

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
class EmotionController {
    @Autowired
    lateinit var emotionService: EmotionService

    @Autowired
    lateinit var emotionsNameService: EmotionsNameService

    private val mappingEmotionUtils: MappingEmotionUtils = MappingEmotionUtils()

    @GetMapping("/emotion")
    fun getAllEmotions(request: HttpServletRequest): ResponseEntity<Iterable<EmotionDto>> {
        val emotions = emotionService.getAllEmotions(request)
                .map{ it -> mappingEmotionUtils.mapToEmotionDto(it) }
        return ResponseEntity.ok(emotions)
    }

    @PostMapping("/emotion")
    fun addEmotion(request: HttpServletRequest, @Validated @RequestBody emotionDto: EmotionDto): ResponseEntity<UUID> {
        val emotionEntity = mappingEmotionUtils.mapToEmotionEntity(emotionDto)

        return ResponseEntity.ok(emotionService.addEmotion(request, emotionEntity))
    }

    @GetMapping("/emotion/name")
    fun getAllEmotionsNames(): ResponseEntity<Iterable<EmotionNameDto>> {
        val emotionNames = emotionsNameService.getAllEmotionsNames()
                .map{ EmotionNameDto(it.id, it.name) }
        return ResponseEntity.ok(emotionNames)
    }

    @PostMapping("/emotion/name")
    fun addEmotionsName(@Validated @RequestBody emotionNameDto: EmotionNameDto): ResponseEntity<UUID> {
        val emotion = EmotionNameEntity(emotionNameDto.id, emotionNameDto.name)
        return ResponseEntity.ok(emotionsNameService.addEmotionsName(emotion))
    }
}
