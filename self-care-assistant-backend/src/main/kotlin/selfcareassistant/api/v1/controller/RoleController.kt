package selfcareassistant.api.v1.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import selfcareassistant.api.v1.dto.EmotionNameDto
import selfcareassistant.api.v1.dto.RoleDto
import selfcareassistant.entity.RoleEntity
import selfcareassistant.service.RoleService
import java.util.*

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
    fun addUser(@RequestBody roleDto: RoleDto): ResponseEntity<UUID> {
        val role = RoleEntity(roleDto.id, roleDto.name)
        return ResponseEntity.ok(roleService.addRole(role))
    }
}