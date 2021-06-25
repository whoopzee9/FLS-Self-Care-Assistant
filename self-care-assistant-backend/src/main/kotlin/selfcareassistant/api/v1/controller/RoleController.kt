package selfcareassistant.api.v1.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
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
    @Operation(summary = "Get list of emotion names")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200")
    ])
    fun getAllRoles(): ResponseEntity<List<RoleDto>> {
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
    @Operation(summary = "Delete role by id")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200", description = "Role successfully deleted"),
        ApiResponse(responseCode = "404", description = "Role does not exist", content = [Content()])
    ])
    fun deleteEmotionName(@RequestParam id: UUID): ResponseEntity<ResponseMessage>  {
        if(!roleService.deleteRole(id)) {
            return ResponseEntity.
            status(HttpStatus.NOT_FOUND).
            body(ResponseMessage("Role with id $id does not exist"));
        }

        return ResponseEntity.ok(ResponseMessage("Role with id $id successfully deleted"))
    }
}