package selfcareassistant.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import org.hibernate.cache.spi.support.AbstractReadWriteAccess
import java.util.*
import javax.persistence.*
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item

import java.util.ArrayList
import javax.persistence.FetchType

@Entity
@Table(name = "usr")
data class User(var name: String, var email: String, var password: String) {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    var id: UUID = UUID.randomUUID()

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    var roles: Set<Role>? = setOf(Role("ROLE_USER"))

    @JsonIgnore
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "user")
    var emotions: List<Emotion> = ArrayList()
}
