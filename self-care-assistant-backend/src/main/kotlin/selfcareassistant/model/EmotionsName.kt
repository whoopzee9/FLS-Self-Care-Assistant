package selfcareassistant.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "emotionsName")
data class EmotionsName(var name: String) {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    var id: UUID = UUID.randomUUID()

    @JsonIgnore
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "emotionsName")
    var emotions: List<Emotion> = ArrayList()
}
