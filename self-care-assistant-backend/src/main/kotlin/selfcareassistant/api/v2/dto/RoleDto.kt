package selfcareassistant.api.v2.dto

import java.util.*

class RoleDto(var name: String) {
    var id: UUID? = null

    constructor(id: UUID?, name: String): this(name) {
        this.id = id
    }
}