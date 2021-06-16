package selfcareassistant.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import selfcareassistant.jwt.JwtProvider
import selfcareassistant.model.ResponseMessage
import selfcareassistant.model.User
import selfcareassistant.model.UserCredentials
import selfcareassistant.service.AppUserDetailsService

@RestController
@CrossOrigin
class AuthController() {

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
        return ResponseEntity.ok(jwt)
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody user: User): ResponseEntity<Any> {
        if (appUserDetailsService.emailExists(user.email)) {
            return ResponseEntity(ResponseMessage("User with email " + user.email + "already exists!"),
                    HttpStatus.BAD_REQUEST)
        }

        user.password = bCryptPasswordEncoder.encode(user.password)
        appUserDetailsService.saveUser(user)

        return ResponseEntity(ResponseMessage("User registered successfully!"), HttpStatus.OK)
    }
}
