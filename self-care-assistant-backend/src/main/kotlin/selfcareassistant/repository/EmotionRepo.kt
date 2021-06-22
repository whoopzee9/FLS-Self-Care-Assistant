package selfcareassistant.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import selfcareassistant.entity.EmotionEntity
import selfcareassistant.entity.EmotionNameEntity
import selfcareassistant.entity.UserEntity
import java.util.*

@Repository
interface EmotionRepo: CrudRepository<EmotionEntity, UUID> {
    fun findAllByUserAndCreateDateBetweenAndEmotionNameAndIntensityBetween(userEntity: UserEntity,
                                                                           lhsDate: Date,
                                                                           rhsDate: Date,
                                                                           emotionNameEntity: EmotionNameEntity,
                                                                           lhsIntensity: Byte,
                                                                           rhsIntensity: Byte): Iterable<EmotionEntity>
    fun findAllByUserAndCreateDateBetween(userEntity: UserEntity,
                                          lhsDate: Date,
                                          rhsDate: Date): Iterable<EmotionEntity>
    fun findAllByUser(userEntity: UserEntity): Iterable<EmotionEntity>
}
