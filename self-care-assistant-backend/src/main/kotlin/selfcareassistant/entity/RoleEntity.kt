package selfcareassistant.entity

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "role")
class RoleEntity(var name: String)  {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    var id: UUID? = null

    @ManyToMany(mappedBy = "roles")
    var users: List<UserEntity> = ArrayList()

    constructor(id: UUID?, name: String): this(name) {
        this.id = id
    }
}