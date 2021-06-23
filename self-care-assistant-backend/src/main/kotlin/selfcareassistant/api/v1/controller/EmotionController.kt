package selfcareassistant.api.v1.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import selfcareassistant.api.v1.dto.EmotionDto
import selfcareassistant.api.v1.dto.EmotionFilterDto
import selfcareassistant.api.v1.dto.EmotionNameDto
import selfcareassistant.api.v1.dto.ResponseMessage
import selfcareassistant.api.v1.dto.util.MappingEmotionUtils
import selfcareassistant.entity.EmotionNameEntity
import selfcareassistant.service.EmotionService
import selfcareassistant.service.EmotionsNameService
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
class EmotionController {
    @Autowired
    lateinit var emotionService: EmotionService

    @Autowired
    lateinit var emotionsNameService: EmotionsNameService

    private val mappingEmotionUtils: MappingEmotionUtils = MappingEmotionUtils()

    @PostMapping("/emotion/filter")
    fun getEmotions(
            request: HttpServletRequest,
            @RequestBody emotionFilter: EmotionFilterDto)
            : ResponseEntity<Iterable<EmotionDto>> {

        val emotions = emotionService.getEmotionsByDateAndEmotionNames(request, emotionFilter.lhsDate,
                emotionFilter.rhsDate, emotionFilter.emotionNames)
                .map{ it -> mappingEmotionUtils.mapToEmotionDto(it) }
        return ResponseEntity.ok(emotions)
    }

    @GetMapping("/emotion")
    fun getEmotions(request: HttpServletRequest): ResponseEntity<Iterable<EmotionDto>> {
        val emotions = emotionService.getAllEmotions(request)
                .map{ it -> mappingEmotionUtils.mapToEmotionDto(it) }
        return ResponseEntity.ok(emotions)
    }

    @PostMapping("/emotion")
    fun addEmotion(request: HttpServletRequest, @RequestBody @Valid emotion: EmotionDto): ResponseEntity<ResponseMessage>  {
        return ResponseEntity.
            ok(ResponseMessage(emotionService.addEmotion(request, mappingEmotionUtils.mapToEmotionEntity(emotion)).toString()))
    }

    @GetMapping("/emotion/name")
    fun getEmotionsNames(): ResponseEntity<Iterable<EmotionNameDto>> {
        val emotionNames = emotionsNameService.getAllEmotionsNames()
                .map{ EmotionNameDto(it.id, it.name) }
        return ResponseEntity.ok(emotionNames)
    }

    @PostMapping("/emotion/name")
    fun addEmotionsName(@Valid @RequestBody emotionNameDto: EmotionNameDto): ResponseEntity<ResponseMessage> {
        val emotion = EmotionNameEntity(emotionNameDto.id, emotionNameDto.name)
        return ResponseEntity.ok(ResponseMessage(emotionsNameService.addEmotionsName(emotion).toString()))
    }
}
