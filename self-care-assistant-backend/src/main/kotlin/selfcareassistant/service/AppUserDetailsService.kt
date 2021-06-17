package selfcareassistant.service

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import selfcareassistant.model.User

interface AppUserDetailsService: UserDetailsService {
  @Throws(UsernameNotFoundException::class)
  override fun loadUserByUsername(username: String?): UserDetails

  @Throws(UsernameNotFoundException::class)
  fun loadUserByEmail(email: String?): User

  fun saveUser(user: User): Boolean
  fun emailExists(email: String): Boolean
}
