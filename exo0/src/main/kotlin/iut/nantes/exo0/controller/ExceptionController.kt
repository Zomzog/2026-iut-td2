package iut.nantes.exo0.controller

import iut.nantes.exo0.errors.ImATeapotException
import iut.nantes.exo0.errors.MyExceptionWithBody
import iut.nantes.exo0.errors.MyFirstException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Demo endpoints: each one throws an exception, to practice error handling.
 */
@RestController
class ExceptionController {

    @GetMapping("/exception/MyFirstException")
    fun myFirstException() {
        throw MyFirstException("My first exception")
    }

    @GetMapping("/exception/MyExceptionWithBody")
    fun myExceptionWithBody() {
        throw MyExceptionWithBody("My exception with body")
    }

    @GetMapping("/exception/ImATeapotException")
    fun imATeapotException() {
        throw ImATeapotException("I'm a teapot")
    }
}
