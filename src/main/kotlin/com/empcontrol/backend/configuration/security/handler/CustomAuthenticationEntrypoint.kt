package com.empcontrol.backend.configuration.security.handler

import com.empcontrol.backend.model.ApiException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Component
class CustomAuthenticationEntrypoint: AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        val apiException = ApiException()
        apiException.backendMessage = authException!!.localizedMessage
        apiException.url = request!!.requestURL.toString()
        apiException.method = request.method
        apiException.message = "Authentication failed: Please provide valid credentials to access this resource."
        apiException.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)

        response!!.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.UNAUTHORIZED.value()
        val mapper = ObjectMapper().registerModules(JavaTimeModule())
        response.writer.write(mapper.writeValueAsString(apiException))

    }
}