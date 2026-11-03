package iut.nantes.exo0.config

import iut.nantes.exo0.errors.ImATeapotException
import iut.nantes.exo0.errors.MyExceptionWithBody
import iut.nantes.exo0.errors.MyFirstException
import tools.jackson.databind.exc.InvalidFormatException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
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

    @ExceptionHandler(ImATeapotException::class)
    fun handleImATeapot(e: ImATeapotException): ProblemDetail =
        ProblemDetail.forStatusAndDetail(HttpStatus.I_AM_A_TEAPOT, e.message)

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest,
    ): ResponseEntity<Any>? {
        val cause = ex.cause
        return if (cause is InvalidFormatException) {
            val field = cause.path.joinToString(".") { it.propertyName }
            val detail = "Invalid value '${cause.value}' for field '$field'"
            val problemDetail = ProblemDetail.forStatusAndDetail(status, detail)
            handleExceptionInternal(ex, problemDetail, headers, status, request)
        } else {
            super.handleHttpMessageNotReadable(ex, headers, status, request)
        }
    }
}

data class ErrorDto(val message: String?, val status: Int)
