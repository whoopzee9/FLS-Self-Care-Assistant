package selfcareassistant.jwt

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.web.filter.OncePerRequestFilter
import selfcareassistant.exceptions.NotValidJwtException
import selfcareassistant.model.Constants.TOKEN_HEADER
import selfcareassistant.model.Constants.TOKEN_PREFIX
import selfcareassistant.model.User
import selfcareassistant.service.AppUserDetailsService
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthTokenFilter: OncePerRequestFilter() {

    @Autowired
    lateinit var tokenProvider: JwtProvider

    @Autowired
    lateinit var appUserDetailsService: AppUserDetailsService

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {

            val jwt = getJwt(request)
            if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
                val email = tokenProvider.getUserNameFromJwtToken(jwt)

                val userDetails = appUserDetailsService.loadUserByUsername(email)
                val authentication = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: Exception) {
            logger.error("Can NOT set user authentication -> Message: {}", e)
        }

        filterChain.doFilter(request, response)
    }

    private fun getJwt(request: HttpServletRequest): String? {
        val authHeader = request.getHeader(TOKEN_HEADER)

        return if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            authHeader.replace(TOKEN_PREFIX, "")
        } else null
    }

    fun getUserFromJwtToken(request: HttpServletRequest): User {
        val jwt = getJwt(request)
        if (jwt != null && tokenProvider.validateJwtToken(jwt)) {
            val email = tokenProvider.getUserNameFromJwtToken(jwt)
            return appUserDetailsService.loadUserByEmail(email)
        } else {
            throw NotValidJwtException("JWT Token is not valid")
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtAuthTokenFilter::class.java)
    }
}