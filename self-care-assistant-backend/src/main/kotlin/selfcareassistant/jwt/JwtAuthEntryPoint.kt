package selfcareassistant.jwt

import io.jsonwebtoken.io.IOException
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthEntryPoint : AuthenticationEntryPoint {

    @Throws(IOException::class, ServletException::class)
    override fun commence(request: HttpServletRequest,
                          response: HttpServletResponse,
                          e: AuthenticationException) {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid credentials")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtAuthEntryPoint::class.java)
    }
}