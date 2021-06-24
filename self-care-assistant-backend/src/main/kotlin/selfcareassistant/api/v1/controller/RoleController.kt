package selfcareassistant.api.v1.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import selfcareassistant.api.v1.dto.ResponseMessage
import selfcareassistant.api.v1.dto.RoleDto
import selfcareassistant.entity.RoleEntity
import selfcareassistant.service.RoleService
import java.util.*
import javax.validation.Valid

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
class RoleController {
    @Autowired
    lateinit var roleService: RoleService

    @GetMapping("/role")
    fun getAllRoles(): ResponseEntity<Iterable<RoleDto>> {
        val roles = roleService.getAllRoles()
                .map{ RoleDto(it.id, it.name) }
        return ResponseEntity.ok(roles)
    }

    @PostMapping("/role")
    fun addUser(@Valid @RequestBody roleDto: RoleDto): ResponseEntity<ResponseMessage> {
        val role = RoleEntity(roleDto.id, roleDto.name)
        return ResponseEntity.ok(ResponseMessage(roleService.addRole(role).toString()))
    }

    @DeleteMapping("/role")
    fun deleteEmotionName(@RequestParam id: UUID): ResponseEntity<ResponseMessage>  {
        if(!roleService.deleteRole(id)) {
            return ResponseEntity.
            status(HttpStatus.NOT_FOUND).
            body(ResponseMessage("Emotion name with id $id does not exist"));
        }

        return ResponseEntity.ok(ResponseMessage("Emotion name with id $id successfully deleted"))
    }
}