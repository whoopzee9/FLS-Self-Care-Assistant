package selfcareassistant.entity

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "role")
class RoleEntity(var name: String)  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: UUID? = null

    @ManyToMany(cascade = [CascadeType.ALL], mappedBy = "roles")
    var users: List<UserEntity> = ArrayList()

    constructor(id: UUID?, name: String): this(name) {
        this.id = id
    }
}