package selfcareassistant.api.v1.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import selfcareassistant.api.v1.dto.util.MappingEmotionUtils
import selfcareassistant.entity.EmotionNameEntity
import selfcareassistant.service.EmotionService
import selfcareassistant.service.EmotionNameService
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import selfcareassistant.api.v1.dto.*
import selfcareassistant.api.v1.dto.util.MappingEmotionNameUtils
import selfcareassistant.api.v1.dto.util.MappingNewEmotionUtils

@RestController
@CrossOrigin()
@RequestMapping("/api/v1")
@Validated
class EmotionController {
    @Autowired
    lateinit var emotionService: EmotionService

    @Autowired
    lateinit var emotionNameService: EmotionNameService

    private val mappingEmotionNameUtils: MappingEmotionNameUtils = MappingEmotionNameUtils()
    private val mappingEmotionUtils: MappingEmotionUtils = MappingEmotionUtils()
    private val mappingNewEmotionUtils: MappingNewEmotionUtils = MappingNewEmotionUtils()

    @PostMapping("/emotion/filter")
    @Operation(summary = "Filter emotions by date and emotion name")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200")
    ])
    fun getEmotions(
            request: HttpServletRequest,
            @Valid @RequestBody emotionFilter: EmotionFilterDto)
            : ResponseEntity<List<EmotionDto>> {

        val emotions = emotionService.getEmotionsByDateAndEmotionNames(request, emotionFilter.lhsDate,
                emotionFilter.rhsDate, emotionFilter.emotionNames)
                .map{ it -> mappingEmotionUtils.mapToEmotionDto(it) }
        return ResponseEntity.ok(emotions)
    }

    @GetMapping("/emotion")
    @Operation(summary = "Get list of emotions")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200")
    ])
    fun getEmotions(request: HttpServletRequest): ResponseEntity<List<EmotionDto>> {
        val emotions = emotionService.getAllEmotions(request)
                .map{ it -> mappingEmotionUtils.mapToEmotionDto(it) }
        return ResponseEntity.ok(emotions)
    }

    @PostMapping("/emotion")
    @Operation(summary = "Add emotion")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Emotion successfully saved")
    ])
    fun addEmotion(request: HttpServletRequest,@RequestBody @Valid newEmotion: NewEmotionDto): ResponseEntity<ResponseMessage>  {
        return ResponseEntity<ResponseMessage>(ResponseMessage(emotionService.addEmotion(request,
                mappingNewEmotionUtils.mapToEmotionEntity(newEmotion)).toString()),
                HttpStatus.CREATED)
    }

    @DeleteMapping("/emotion")
    @Operation(summary = "Delete emotion with id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Emotion successfully deleted"),
        ApiResponse(responseCode = "404", description = "Emotion does not exist", content = [Content()])
    ])
    fun deleteEmotion(@Parameter(description = "id of emotion to be deleted")
                      @RequestParam id: UUID): ResponseEntity<ResponseMessage>  {
        if(!emotionService.deleteEmotion(id)) {
            return ResponseEntity<ResponseMessage>(ResponseMessage("Emotion with id $id does not exist"), HttpStatus.NOT_FOUND)
        }

        return ResponseEntity.ok(ResponseMessage("Emotion with id $id successfully deleted"))
    }

    @PutMapping("/emotion")
    @Operation(summary = "Change emotion")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Emotion successfully saved"),
        ApiResponse(responseCode = "204", description = "Emotion successfully changed")
    ])
    fun changeEmotion(@RequestBody @Valid emotionDto: EmotionDto): ResponseEntity<ResponseMessage>  {
        if(!emotionService.changeEmotion(mappingEmotionUtils.mapToEmotionEntity(emotionDto))) {
            return ResponseEntity<ResponseMessage>(ResponseMessage("Emotion successfully saved"), HttpStatus.CREATED)
        }

        return ResponseEntity<ResponseMessage>(ResponseMessage("Emotion successfully changed"), HttpStatus.NO_CONTENT)
    }

    @GetMapping("/emotion/name")
    @Operation(summary = "Get list of emotion names")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200")
    ])
    fun getEmotionNames(): ResponseEntity<List<EmotionNameDto>> {
        val emotionNames = emotionNameService.getAllEmotionNames()
                .map{ mappingEmotionNameUtils.mapToEmotionNameDto(it) }
        return ResponseEntity.ok(emotionNames)
    }

    @PostMapping("/emotion/name")
    @Operation(summary = "Add emotion name")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Emotion successfully saved")
    ])
    fun addEmotionName(@Valid @RequestBody newEmotionNameDto: NewEmotionNameDto): ResponseEntity<ResponseMessage> {
        val emotionName = EmotionNameEntity(newEmotionNameDto.name)
        return ResponseEntity.ok(ResponseMessage(emotionNameService.addEmotionName(emotionName).toString()))
    }

    @DeleteMapping("/emotion/name")
    @Operation(summary = "Delete emotion name with id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Emotion name successfully deleted"),
        ApiResponse(responseCode = "404", description = "Emotion name does not exist", content = [Content()])
    ])
    fun deleteEmotionName(@RequestParam id: UUID): ResponseEntity<ResponseMessage>  {
        if(!emotionNameService.deleteEmotionName(id)) {
            return ResponseEntity.
                status(HttpStatus.NOT_FOUND).
                body(ResponseMessage("Emotion name with id $id does not exist"));
        }

        return ResponseEntity.ok(ResponseMessage("Emotion name with id $id successfully deleted"))
    }

    @PutMapping("/emotion/name")
    @Operation(summary = "Change emotion name")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Emotion successfully saved"),
        ApiResponse(responseCode = "204", description = "Emotion successfully changed")
    ])
    fun changeEmotionName(@RequestBody @Valid emotionNameDto: EmotionNameDto): ResponseEntity<ResponseMessage>  {
        val emotionName = EmotionNameEntity(emotionNameDto.id, emotionNameDto.name)
        if(!emotionNameService.changeEmotionName(emotionName)) {
            return ResponseEntity<ResponseMessage>(ResponseMessage("Emotion name successfully saved"), HttpStatus.CREATED)
        }

        return ResponseEntity<ResponseMessage>(ResponseMessage("Emotion name successfully changed"), HttpStatus.NO_CONTENT)
    }
}
