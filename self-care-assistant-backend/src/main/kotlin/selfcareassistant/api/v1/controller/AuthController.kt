package selfcareassistant.api.v1.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import selfcareassistant.jwt.JwtProvider
import selfcareassistant.api.v1.dto.JWTResponse
import selfcareassistant.api.v1.dto.ResponseMessage
import selfcareassistant.api.v1.dto.UserCredentials
import selfcareassistant.api.v1.dto.UserRegisterDto
import selfcareassistant.api.v1.dto.util.MappingUserRegisterUtils
import selfcareassistant.service.AppUserDetailsService
import javax.validation.Valid

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
class AuthController {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var appUserDetailsService: AppUserDetailsService

    @Autowired
    lateinit var jwtProvider: JwtProvider

    @Autowired
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @PostMapping("/signin")
    @Operation(summary = "Autorization")
    @ApiResponses(
            ApiResponse(responseCode = "200", description = "User has successfully logged in"),
            ApiResponse(responseCode = "401", description = "Invalid email or password", content = [Content()])
    )
    protected fun authenticateUser(@Valid @RequestBody loginRequest: UserCredentials): ResponseEntity<JWTResponse> {

        val userCandidate: UserDetails = appUserDetailsService.loadUserByUsername(loginRequest.email)
        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password, userCandidate.authorities))
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtProvider.generateJwtToken(loginRequest.email)
        return ResponseEntity.ok(JWTResponse(jwt))
    }

    @PostMapping("/signup")
    @Operation(summary = "Registration")
    @ApiResponses(
            ApiResponse(responseCode = "200", description = "User has successfully registered"),
            ApiResponse(responseCode = "400", description = "User already exists!", content = [Content()])
    )
    fun registerUser(@Valid @RequestBody userRegisterDto: UserRegisterDto): ResponseEntity<Any> {
        if (appUserDetailsService.emailExists(userRegisterDto.email)) {
            return ResponseEntity(ResponseMessage("User with email " + userRegisterDto.email + " already exists!"),
                    HttpStatus.BAD_REQUEST)
        }

        val user = MappingUserRegisterUtils().mapToUserEntity(userRegisterDto)
        user.password = bCryptPasswordEncoder.encode(user.password)
        appUserDetailsService.saveUser(user)

        return ResponseEntity(ResponseMessage("User registered successfully!"), HttpStatus.OK)
    }
}
