package selfcareassistant.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import selfcareassistant.model.Role
import selfcareassistant.service.RoleService
import java.util.*

@RestController
@CrossOrigin
class RoleController {
    @Autowired
    lateinit var roleService: RoleService

    @GetMapping("/roles")
    fun getAllRoles(): ResponseEntity<Iterable<Role>> {
        return ResponseEntity.ok(roleService.getAllRoles())
    }

    @PostMapping("/add-role")
    fun addUser(@RequestBody role: Role): ResponseEntity<UUID> {
        return ResponseEntity.ok(roleService.addRole(role))
    }
}