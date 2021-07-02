package selfcareassistant.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "emotionsName")
class EmotionNameEntity(var name: String)  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @JsonIgnore
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "emotionName")
    var emotions: List<EmotionEntity> = ArrayList()

    constructor(id: UUID?, name: String): this(name) {
        this.id = id
    }
}
