package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema
class RoleDto(@Schema(
              description = "Name of role",
              example = "user")
                    var name: String) {
    var id: UUID? = null

    constructor(id: UUID?, name: String): this(name) {
        this.id = id
    }
}