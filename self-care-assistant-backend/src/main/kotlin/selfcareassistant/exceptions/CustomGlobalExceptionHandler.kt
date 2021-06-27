package selfcareassistant.exceptions

import org.springframework.beans.InvalidPropertyException
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import java.util.stream.Collectors
import org.springframework.web.context.request.WebRequest
import org.springframework.http.HttpStatus
import org.springframework.http.HttpHeaders
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletResponse
import javax.validation.ConstraintViolationException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

import org.springframework.web.bind.MissingServletRequestParameterException

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class CustomGlobalExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders,
                                              status: HttpStatus, request: WebRequest): ResponseEntity<Any> {
        val body: MutableMap<String, Any> = LinkedHashMap()
        body["timestamp"] = Date()
        body["status"] = status.value()

        val errors = ex.bindingResult
                .fieldErrors
                .stream()
                .map { x: FieldError -> x.defaultMessage }
                .collect(Collectors.toList())
        body["message"] = errors
        return ResponseEntity(body, headers, status)
    }

    @ExceptionHandler(InvalidPropertyException::class)
    @Throws(IOException::class)
    fun constraintViolationException(ex: InvalidPropertyException, response: HttpServletResponse) {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.propertyName + " should not be null")
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatch(
            ex: MethodArgumentTypeMismatchException, request: WebRequest?): ResponseEntity<Any?>? {
        val error = ex.name + " should be of type " + ex.requiredType!!.name
        val apiError = ApiError(HttpStatus.BAD_REQUEST, ex.localizedMessage)
        return ResponseEntity(
                apiError, HttpHeaders(), apiError.status)
    }
}