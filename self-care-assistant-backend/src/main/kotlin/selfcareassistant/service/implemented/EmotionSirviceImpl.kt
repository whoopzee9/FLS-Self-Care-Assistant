package selfcareassistant.service.implemented

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import selfcareassistant.jwt.JwtAuthTokenFilter
import selfcareassistant.model.Emotion
import selfcareassistant.repository.EmotionRepo
import selfcareassistant.service.EmotionService
import java.util.*
import javax.servlet.http.HttpServletRequest

@Service
class EmotionSirviceImpl: EmotionService {
    @Autowired
    lateinit var emotionRepo: EmotionRepo

    @Autowired
    lateinit var jwtAuthTokenFilter: JwtAuthTokenFilter

    override fun addEmotion(request: HttpServletRequest, emotion: Emotion): UUID {
        val user = jwtAuthTokenFilter.getUserFromJwtToken(request)

        emotion.user = user
        return emotionRepo.save(emotion).id
    }

    override fun getAllEmotions(request: HttpServletRequest): Iterable<Emotion> {
        val user = jwtAuthTokenFilter.getUserFromJwtToken(request)

        return emotionRepo.findAllByUser(user)
    }
}
