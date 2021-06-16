package selfcareassistant.jwt

import io.jsonwebtoken.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import selfcareassistant.model.Constants.JWT_EXPIRATION
import selfcareassistant.model.Constants.JWT_SECRET
import selfcareassistant.repository.UserRepo
import java.security.SignatureException
import java.util.*


@Component
class JwtProvider {

    private val logger: Logger = LoggerFactory.getLogger(JwtProvider::class.java)

    fun generateJwtToken(email: String): String {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(Date())
                .setExpiration(Date((Date()).time + JWT_EXPIRATION))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact()
    }

    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature -> Message: {} ", e)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token -> Message: {}", e)
        } catch (e: ExpiredJwtException) {
            logger.error("Expired JWT token -> Message: {}", e)
        } catch (e: UnsupportedJwtException) {
            logger.error("Unsupported JWT token -> Message: {}", e)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty -> Message: {}", e)
        }

        return false
    }

    fun getUserNameFromJwtToken(token: String): String {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody().getSubject()
    }
}