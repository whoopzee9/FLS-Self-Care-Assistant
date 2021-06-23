package selfcareassistant.service.implemented

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import selfcareassistant.api.v1.dto.EmotionNameFilterDto
import selfcareassistant.jwt.JwtAuthTokenFilter
import selfcareassistant.entity.EmotionEntity
import selfcareassistant.entity.EmotionNameEntity
import selfcareassistant.repository.EmotionRepo
import selfcareassistant.service.EmotionService
import java.util.*
import javax.servlet.http.HttpServletRequest
import kotlin.collections.ArrayList

@Service
class EmotionSirviceImpl: EmotionService {
    @Autowired
    lateinit var emotionRepo: EmotionRepo

    @Autowired
    lateinit var jwtAuthTokenFilter: JwtAuthTokenFilter

    override fun addEmotion(request: HttpServletRequest, emotionEntity: EmotionEntity): UUID {
        val user = jwtAuthTokenFilter.getUserFromJwtToken(request)

        emotionEntity.user = user
        return emotionRepo.save(emotionEntity).id!!
    }

    override fun getEmotionsByDateAndEmotionNames(request: HttpServletRequest,
                                                  lhsDate: Date,
                                                  rhsDate: Date,
                                                  emotionNames: List<EmotionNameFilterDto>): Iterable<EmotionEntity> {
        val user = jwtAuthTokenFilter.getUserFromJwtToken(request)

        return if (emotionNames.isNotEmpty()) {
            val emotions: ArrayList<EmotionEntity> = ArrayList()

            for (emotionNameFilter in emotionNames) {
                val emotionName = EmotionNameEntity(emotionNameFilter.emotionName?.id, emotionNameFilter.emotionName!!.name)
                emotions.addAll(emotionRepo.findAllByUserAndCreateDateBetweenAndEmotionNameAndIntensityBetween(user,
                        lhsDate, rhsDate, emotionName, emotionNameFilter.lhsIntensity, emotionNameFilter.rhsIntensity))
            }

            emotions
        } else {
            print("else")
            emotionRepo.findAllByUserAndCreateDateBetween(user, lhsDate, rhsDate)
        }
    }

    override fun getAllEmotions(request: HttpServletRequest): Iterable<EmotionEntity> {
        val user = jwtAuthTokenFilter.getUserFromJwtToken(request)

        return emotionRepo.findAllByUser(user)
    }

    override fun deleteEmotion(id: UUID): Boolean {
        if(!emotionRepo.findById(id).isPresent()) {
            return false
        }

        emotionRepo.deleteById(id)
        return true
    }
}
