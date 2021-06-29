package selfcareassistant.api.v1.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import selfcareassistant.api.v1.dto.EmotionDto
import selfcareassistant.api.v1.dto.NewRoleDto
import selfcareassistant.api.v1.dto.ResponseMessage
import selfcareassistant.api.v1.dto.RoleDto
import selfcareassistant.api.v1.dto.util.MappingRoleUtils
import selfcareassistant.entity.RoleEntity
import selfcareassistant.service.RoleService
import java.util.*
import javax.validation.Valid

@RestController
@CrossOrigin()
@RequestMapping("/api/v1")
class RoleController {
    @Autowired
    lateinit var roleService: RoleService

    private val mappingRoleUtils: MappingRoleUtils = MappingRoleUtils()

    @GetMapping("/role")
    @Operation(summary = "Get list of emotion names")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200")
    ])
    fun getAllRoles(): ResponseEntity<List<RoleDto>> {
        val roles = roleService.getAllRoles()
                .map{ mappingRoleUtils.mapToRoleDto(it) }
        return ResponseEntity.ok(roles)
    }

    @PostMapping("/role")
    @Operation(summary = "Add role")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Role successfully saved")
    ])
    fun addUser(@Valid @RequestBody newRoleDto: NewRoleDto): ResponseEntity<ResponseMessage> {
        val role = RoleEntity(newRoleDto.name)
        return ResponseEntity.ok(ResponseMessage(roleService.addRole(role).toString()))
    }

    @PutMapping("/role")
    @Operation(summary = "Change role")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Role successfully saved"),
        ApiResponse(responseCode = "204", description = "Role successfully changed")
    ])
    fun changeEmotion(@RequestBody @Valid roleDto: RoleDto): ResponseEntity<ResponseMessage>  {
        if(!roleService.changeRole(RoleEntity(roleDto.id, roleDto.name))) {
            return ResponseEntity<ResponseMessage>(ResponseMessage("Role successfully saved"), HttpStatus.CREATED)
        }

        return ResponseEntity<ResponseMessage>(ResponseMessage("Role successfully changed"), HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/role")
    @Operation(summary = "Delete role with id")
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