package selfcareassistant.exceptions

import org.springframework.http.HttpStatus
import java.util.*

data class ApiError(
        val message: List<String>
)