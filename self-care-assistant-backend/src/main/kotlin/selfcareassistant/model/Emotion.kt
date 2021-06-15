package selfcareassistant.model

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "emotions")
data class Emotion(@JoinColumn (name = "emotions_name_id", nullable = false)
                   @ManyToOne (cascade = [CascadeType.REFRESH])
                   var emotionsName: EmotionsName,
                   var name: String, var create_date: Date, var rate: Byte) {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    var id: UUID = UUID.randomUUID();
}
