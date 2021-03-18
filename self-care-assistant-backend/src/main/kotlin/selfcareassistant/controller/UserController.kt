package selfcareassistant.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import selfcareassistant.service.UserService
import java.util.*

@RestController
@RequestMapping("/user")
@CrossOrigin
class UserController {
    @Autowired
    lateinit var userService: UserService

    @GetMapping("/get_id/{name}")
    fun getId(@PathVariable("name") name: String): ResponseEntity<UUID> {
        return ResponseEntity(userService.getId(name), HttpStatus.OK)
    }

    @PostMapping("/add/{name}")
    fun addUser(@PathVariable("name") name: String): ResponseEntity<String> {
        return ResponseEntity(userService.addUser(name), HttpStatus.OK)
    }
}