package selfcareassistant.api.v2.dto

import java.util.*

data class UserCredentials(var email: String, var password: String) {
    var id: UUID? = null
}