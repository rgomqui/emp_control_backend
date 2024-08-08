package com.empcontrol.backend.exception

import com.empcontrol.backend.model.ApiException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.stream.Collectors

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handlerGenericException(request: HttpServletRequest, exception: Exception): ResponseEntity<*> {
        val apiException = ApiException()
        apiException.backendMessage = exception.localizedMessage
        apiException.url = request.requestURL.toString()
        apiException.method = request.method
        apiException.message = "Error interno en el servidor, vuelva a intentarlo"
        apiException.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body<Any>(apiException)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handlerAccesDeniedException(request: HttpServletRequest, accessDeniedException: AccessDeniedException): ResponseEntity<*> {
        val apiException = ApiException()
        apiException.backendMessage = accessDeniedException.localizedMessage
        apiException.url = request.requestURL.toString()
        apiException.method = request.method
        apiException.message = "Access denied to resource."
        apiException.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body<Any>(apiException)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(
        request: HttpServletRequest,
        exception: MethodArgumentNotValidException
    ): ResponseEntity<*> {
        val apiException = ApiException()
        apiException.backendMessage = exception.localizedMessage
        apiException.url = request.requestURL.toString()
        apiException.method = request.method
        apiException.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        apiException.message = "Error en la petición enviada"

        println(
            exception.allErrors.stream().map { each: ObjectError -> each.defaultMessage }
                .collect(Collectors.toList())
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<Any>(apiException)
    }
}