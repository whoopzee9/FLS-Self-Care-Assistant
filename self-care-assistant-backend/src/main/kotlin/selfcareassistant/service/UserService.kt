package selfcareassistant.service

import org.springframework.stereotype.Service
import java.util.*

interface UserService {
    fun getId(name: String): UUID
    fun addUser(name: String): String
}

