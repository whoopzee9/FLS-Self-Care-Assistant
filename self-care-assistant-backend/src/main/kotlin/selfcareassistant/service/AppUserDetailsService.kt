package selfcareassistant.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import selfcareassistant.entity.UserEntity

interface AppUserDetailsService: UserDetailsService {
  @Throws(UsernameNotFoundException::class)
  override fun loadUserByUsername(username: String?): UserDetails

  @Throws(UsernameNotFoundException::class)
  fun loadUserByEmail(email: String?): UserEntity

  fun saveUser(userEntity: UserEntity): Boolean
  fun emailExists(email: String): Boolean
}
