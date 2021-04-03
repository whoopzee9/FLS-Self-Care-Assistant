package selfcareassistant.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import selfcareassistant.model.Emotion
import selfcareassistant.model.EmotionsName
import selfcareassistant.service.EmotionService
import selfcareassistant.service.EmotionsNameService
import java.util.*

@RestController
@RequestMapping("/emotion")
@CrossOrigin
class EmotionController {
    @Autowired
    lateinit var emotionService: EmotionService

    @Autowired
    lateinit var emotionsNameService: EmotionsNameService

    @GetMapping("/get_all_emotions")
    fun getAllEmotions(): ResponseEntity<Iterable<Emotion>> {
        return ResponseEntity(emotionService.getAllEmotions(), HttpStatus.OK)
    }

    @GetMapping("/get_all_emotions_names")
    fun getAllEmotionsNames(): ResponseEntity<Iterable<EmotionsName>> {
        return ResponseEntity(emotionsNameService.getAllEmotionsNames(), HttpStatus.OK)
    }

    @PostMapping("/add_emotion")
    fun addUser(@RequestBody emotion: Emotion): ResponseEntity<UUID> {
        return ResponseEntity(emotionService.addEmotion(emotion), HttpStatus.OK)
    }

    @PostMapping("/add_emotions_name")
    fun addUser(@RequestBody emotionsName: EmotionsName): ResponseEntity<UUID> {
        return ResponseEntity(emotionsNameService.addEmotionsName(emotionsName), HttpStatus.OK)
    }
}
