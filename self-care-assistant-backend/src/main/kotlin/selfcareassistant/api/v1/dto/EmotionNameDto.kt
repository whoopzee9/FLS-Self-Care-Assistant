package selfcareassistant.api.v1.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

@Schema
class EmotionNameDto(@Schema(
                    description = "Name of emotion",
                    example = "гнев")
                            var name: String) {
    var id: UUID? = null

    constructor(id: UUID?, name: String): this(name) {
        this.id = id
    }
}