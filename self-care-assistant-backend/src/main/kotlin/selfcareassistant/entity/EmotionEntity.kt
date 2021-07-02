package selfcareassistant.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import org.jetbrains.annotations.NotNull
import java.util.*
import javax.persistence.*
import javax.persistence.JoinColumn

@Entity
@Table(name = "emotion")
class EmotionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    var createDate: Date = Date()
    var intensity: Byte = 0

    @JoinColumn (name = "emotions_name_id", nullable = false)
    @ManyToOne (cascade = [CascadeType.REFRESH])
    var emotionName: EmotionNameEntity? = null

    @JsonIgnore
    @ManyToOne(cascade = [CascadeType.REFRESH])
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: UserEntity? = null
}
