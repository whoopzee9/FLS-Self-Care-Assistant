package selfcareassistant.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ControllerAdviceRequestError: ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(UsernameNotFoundException::class)])
    fun handleUserAlreadyExists(ex: UsernameNotFoundException, request: WebRequest): ResponseEntity<String> {
        return ResponseEntity(ex.message!!, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(NotValidJwtException::class)])
    fun handleNotValidJwt(ex: UsernameNotFoundException, request: WebRequest): ResponseEntity<String> {
        return ResponseEntity(ex.message!!, HttpStatus.BAD_REQUEST)
    }
}