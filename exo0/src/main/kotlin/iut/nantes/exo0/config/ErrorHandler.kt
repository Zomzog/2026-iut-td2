package iut.nantes.exo0.config

import iut.nantes.exo0.errors.MyExceptionWithBody
import iut.nantes.exo0.errors.MyFirstException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ErrorHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(MyFirstException::class)
    fun handleMyFirstException(e: MyFirstException): ResponseEntity<String> =
        ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.message)

    @ExceptionHandler(MyExceptionWithBody::class)
    fun handleMyExceptionWithBody(e: MyExceptionWithBody): ResponseEntity<ErrorDto> {
        val status = HttpStatus.INTERNAL_SERVER_ERROR
        return ResponseEntity.status(status).body(ErrorDto(e.message, status.value()))
    }
}

data class ErrorDto(val message: String?, val status: Int)
