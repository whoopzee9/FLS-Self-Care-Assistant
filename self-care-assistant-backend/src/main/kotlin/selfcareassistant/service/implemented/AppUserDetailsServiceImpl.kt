package selfcareassistant.service.implemented

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import selfcareassistant.entity.UserEntity
import selfcareassistant.repository.UserRepo
import selfcareassistant.service.AppUserDetailsService
import java.util.stream.Collectors

@Component
class AppUserDetailsServiceImpl: AppUserDetailsService {

    @Autowired
    lateinit var userRepo: UserRepo

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepo.findByEmail(username!!)
                .orElseThrow { throw UsernameNotFoundException("User with email '$username' not found") }

        val authorities: List<GrantedAuthority> = user.roles.stream()
                .map { role -> SimpleGrantedAuthority(role.name) }.collect(Collectors.toList<GrantedAuthority>())

        return org.springframework.security.core.userdetails.User
                .withUsername(user.email)
                .password(user.password)
                .authorities(authorities)
                .build()
    }

    override fun loadUserByEmail(email: String?): UserEntity {
        return userRepo.findByEmail(email!!)
                .orElseThrow { throw UsernameNotFoundException("User with email '$email' not found") }
    }

    override fun saveUser(userEntity: UserEntity): Boolean {
        val userFromDB = userRepo.findByEmail(userEntity.email)
        if (userFromDB.isPresent) {
            return false
        }

        userRepo.save(userEntity)
        return true
    }

    override fun emailExists(email: String): Boolean {
        return userRepo.findByEmail(email).isPresent
    }
}
