package selfcareassistant.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*
import javax.persistence.JoinColumn

@Entity
@Table(name = "emotion")
data class Emotion(var name: String, var create_date: Date, var rate: Byte) {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    var id: UUID = UUID.randomUUID()

    @JoinColumn (name = "emotions_name_id", nullable = false)
    @ManyToOne (cascade = [CascadeType.REFRESH])
    var emotionsName: EmotionsName? = null

    @JsonIgnore
    @ManyToOne(cascade = [CascadeType.REFRESH])
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    var user: User? = null
}
