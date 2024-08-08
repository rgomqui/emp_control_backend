package com.empcontrol.backend.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

data class ApiException(
    var backendMessage: String = "",
    var message: String = "",
    var url: String = "",
    var method: String = "",
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var timestamp: String = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
) {
}
