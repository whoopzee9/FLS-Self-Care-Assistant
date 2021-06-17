package selfcareassistant.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import selfcareassistant.model.Emotion
import selfcareassistant.model.EmotionsName
import selfcareassistant.service.EmotionService
import selfcareassistant.service.EmotionsNameService
import java.util.*
import javax.servlet.http.HttpServletRequest

@RestController
@CrossOrigin
class EmotionController {
    @Autowired
    lateinit var emotionService: EmotionService

    @Autowired
    lateinit var emotionsNameService: EmotionsNameService

    @GetMapping("/emotions")
    fun getAllEmotions(request: HttpServletRequest): ResponseEntity<Iterable<Emotion>> {
        return ResponseEntity.ok(emotionService.getAllEmotions(request))
    }

    @GetMapping("/emotions-names")
    fun getAllEmotionsNames(): ResponseEntity<Iterable<EmotionsName>> {
        return ResponseEntity.ok(emotionsNameService.getAllEmotionsNames())
    }

    @PostMapping("/add-emotion")
    fun addEmotion(request: HttpServletRequest, @RequestBody emotion: Emotion): ResponseEntity<UUID> {
        return ResponseEntity.ok(emotionService.addEmotion(request, emotion))
    }

    @PostMapping("/add-emotions-name")
    fun addEmotionsName(@RequestBody emotionsName: EmotionsName): ResponseEntity<UUID> {
        return ResponseEntity.ok(emotionsNameService.addEmotionsName(emotionsName))
    }
}
