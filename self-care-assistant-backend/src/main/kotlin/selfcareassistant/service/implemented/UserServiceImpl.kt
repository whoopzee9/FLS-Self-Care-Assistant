package selfcareassistant.service.implemented

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import selfcareassistant.model.User
import selfcareassistant.repository.UserRepo
import selfcareassistant.service.UserService
import java.util.*

@Service
class UserServiceImpl : UserService {
    @Autowired
    lateinit var userRepo: UserRepo

    override fun getId(name: String): UUID {
        return userRepo.findByName(name).id
    }

    override fun addUser(name: String): String {
        userRepo.save(User(name))
        return "user $name created"
    }
}