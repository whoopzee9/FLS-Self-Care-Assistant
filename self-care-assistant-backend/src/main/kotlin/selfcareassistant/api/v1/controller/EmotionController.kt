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
import selfcareassistant.service.EmotionNameService
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import org.springframework.http.HttpStatus

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
class EmotionController {
    @Autowired
    lateinit var emotionService: EmotionService

    @Autowired
    lateinit var emotionNameService: EmotionNameService

    private val mappingEmotionUtils: MappingEmotionUtils = MappingEmotionUtils()

    @PostMapping("/emotion/filter")
    fun getEmotions(
            request: HttpServletRequest,
            @RequestBody emotionFilter: EmotionFilterDto)
            : ResponseEntity<List<EmotionDto>> {

        val emotions = emotionService.getEmotionsByDateAndEmotionNames(request, emotionFilter.lhsDate,
                emotionFilter.rhsDate, emotionFilter.emotionNames)
                .map{ it -> mappingEmotionUtils.mapToEmotionDto(it) }
        return ResponseEntity.ok(emotions)
    }

    @ResponseBody
    @GetMapping("/emotion")
    fun getEmotions(request: HttpServletRequest): ResponseEntity<List<EmotionDto>> {
        val emotions = emotionService.getAllEmotions(request)
                .map{ it -> mappingEmotionUtils.mapToEmotionDto(it) }
        return ResponseEntity.ok(emotions)
    }

    @PostMapping("/emotion")
    fun addEmotion(request: HttpServletRequest,@Valid @RequestBody emotion: EmotionDto): ResponseEntity<ResponseMessage>  {
        return ResponseEntity.
            ok(ResponseMessage(emotionService.addEmotion(request, mappingEmotionUtils.mapToEmotionEntity(emotion)).toString()))
    }

    @DeleteMapping("/emotion")
    fun deleteEmotion(@RequestParam id: UUID): ResponseEntity<ResponseMessage>  {
        if(!emotionService.deleteEmotion(id)) {
            //return ResponseEntity.notFound().body(ResponseMessage("Emotion with id '$id' not exist"))
            return ResponseEntity<ResponseMessage>(ResponseMessage("Emotion with id $id does not exist"), HttpStatus.NOT_FOUND)
        }

        return ResponseEntity.ok(ResponseMessage("Emotion with id $id successfully deleted"))
    }

    @GetMapping("/emotion/name")
    fun getEmotionNames(): ResponseEntity<List<EmotionNameDto>> {
        val emotionNames = emotionNameService.getAllEmotionNames()
                .map{ EmotionNameDto(it.id, it.name) }
        return ResponseEntity.ok(emotionNames)
    }

    @PostMapping("/emotion/name")
    fun addEmotionName(@Valid @RequestBody emotionNameDto: EmotionNameDto): ResponseEntity<ResponseMessage> {
        val emotion = EmotionNameEntity(emotionNameDto.id, emotionNameDto.name)
        return ResponseEntity.ok(ResponseMessage(emotionNameService.addEmotionName(emotion).toString()))
    }

    @DeleteMapping("/emotion/name")
    fun deleteEmotionName(@RequestParam id: UUID): ResponseEntity<ResponseMessage>  {
        if(!emotionNameService.deleteEmotionName(id)) {
            return ResponseEntity.
                status(HttpStatus.NOT_FOUND).
                body(ResponseMessage("Emotion name with id $id does not exist"));
        }

        return ResponseEntity.ok(ResponseMessage("Emotion name with id $id successfully deleted"))
    }
}
