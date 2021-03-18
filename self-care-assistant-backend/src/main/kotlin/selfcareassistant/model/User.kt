package selfcareassistant.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User(var name: String) {
    @Id
    val id: UUID = UUID.randomUUID()
}