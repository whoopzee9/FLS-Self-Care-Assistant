package selfcareassistant.api.v1.controller

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
    fun authenticateUser(@Valid @RequestBody loginRequest: UserCredentials): ResponseEntity<Any> {

        val userCandidate: UserDetails = appUserDetailsService.loadUserByUsername(loginRequest.email)
        val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password, userCandidate.authorities))
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtProvider.generateJwtToken(loginRequest.email)
        return ResponseEntity.ok(ResponseMessage(jwt))
    }

    @PostMapping("/signup")
    fun registerUser(@Valid @RequestBody userRegisterDto: UserRegisterDto): ResponseEntity<Any> {
        if (appUserDetailsService.emailExists(userRegisterDto.email)) {
            return ResponseEntity(ResponseMessage("User with email " + userRegisterDto.email + "already exists!"),
                    HttpStatus.BAD_REQUEST)
        }

        val user = MappingUserRegisterUtils().mapToUserEntity(userRegisterDto)
        user.password = bCryptPasswordEncoder.encode(user.password)
        appUserDetailsService.saveUser(user)

        return ResponseEntity(ResponseMessage("User registered successfully!"), HttpStatus.OK)
    }
}
