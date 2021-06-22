package selfcareassistant.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import selfcareassistant.api.v2.dto.ResponseMessage

@ControllerAdvice
class ControllerAdviceRequestError: ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [(UsernameNotFoundException::class)])
    fun handleUserAlreadyExists(ex: UsernameNotFoundException, request: WebRequest): ResponseEntity<ResponseMessage> {
        return ResponseEntity(ResponseMessage(ex.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(NotValidJwtException::class)])
    fun handleNotValidJwt(ex: UsernameNotFoundException, request: WebRequest): ResponseEntity<ResponseMessage> {
        return ResponseEntity(ResponseMessage(ex.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(NoSuchElementException::class)])
    fun handleNoSuchElement(ex: UsernameNotFoundException, request: WebRequest): ResponseEntity<ResponseMessage> {
        return ResponseEntity(ResponseMessage(ex.message), HttpStatus.BAD_REQUEST)
    }
}