package selfcareassistant.service

import java.util.*

interface UserService {
    fun getId(name: String): UUID
    fun addUser(name: String): String
}