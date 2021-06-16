package selfcareassistant.model

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "role")
data class Role (val name: String)  {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    var id: UUID = UUID.randomUUID()

    @ManyToMany(mappedBy = "roles")
    var users: List<User> = ArrayList()
}