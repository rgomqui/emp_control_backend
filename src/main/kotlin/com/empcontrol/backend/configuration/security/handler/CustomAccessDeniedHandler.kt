package com.empcontrol.backend.configuration.security.handler

import com.empcontrol.backend.model.ApiException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class CustomAccessDeniedHandler: AccessDeniedHandler{
    override fun handle(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        accessDeniedException: AccessDeniedException?
    ) {
        val apiException = ApiException()
        apiException.backendMessage = accessDeniedException!!.localizedMessage
        apiException.url = request!!.requestURL.toString()
        apiException.method = request.method
        apiException.message = "Access denied: You do not have permission to perform this action."
        apiException.timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)

        response!!.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpStatus.FORBIDDEN.value()
        val mapper = ObjectMapper().registerModules(JavaTimeModule())
        response.writer.write(mapper.writeValueAsString(apiException))
    }
}