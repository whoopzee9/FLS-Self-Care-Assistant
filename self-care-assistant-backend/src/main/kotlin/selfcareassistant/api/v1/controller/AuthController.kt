package selfcareassistant.api.v1.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import selfcareassistant.jwt.JwtProvider
import selfcareassistant.api.v1.dto.JWTResponse
import selfcareassistant.api.v1.dto.ResponseMessage
import selfcareassistant.entity.UserEntity
import selfcareassistant.api.v1.dto.UserCredentials
import selfcareassistant.api.v1.dto.UserDto
import selfcareassistant.api.v1.dto.util.MappingUserUtils
import selfcareassistant.service.AppUserDetailsService

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
    fun authenticateUser(@RequestBody loginRequest: UserCredentials): ResponseEntity<Any> {

        val userCandidate: UserDetails = appUserDetailsService.loadUserByUsername(loginRequest.email)
        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password, userCandidate.authorities))
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtProvider.generateJwtToken(loginRequest.email)
        return ResponseEntity.ok(JWTResponse(jwt))
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody userDto: UserDto): ResponseEntity<Any> {
        if (appUserDetailsService.emailExists(userDto.email)) {
            return ResponseEntity(ResponseMessage("User with email " + userDto.email + "already exists!"),
                    HttpStatus.BAD_REQUEST)
        }

        val user = MappingUserUtils().mapToUserEntity(userDto)
        user.password = bCryptPasswordEncoder.encode(user.password)
        appUserDetailsService.saveUser(user)

        return ResponseEntity(ResponseMessage("User registered successfully!"), HttpStatus.OK)
    }
}
