package selfcareassistant.api.v1.dto

import java.util.*

class EmotionNameDto(var name: String) {
    var id: UUID? = null

    constructor(id: UUID?, name: String): this(name) {
        this.id = id
    }
}