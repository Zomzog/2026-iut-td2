package iut.nantes.exo0.config

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
}
