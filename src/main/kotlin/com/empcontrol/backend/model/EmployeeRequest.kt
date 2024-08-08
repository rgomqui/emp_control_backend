package com.empcontrol.backend.model

import com.empcontrol.backend.enums.EmployeeRoles
import com.empcontrol.backend.enums.EmployeeStatus
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class EmployeeRequest(
    var status: EmployeeStatus,

    var role: EmployeeRoles,

    var name: String,

    var birthdate: LocalDate,

    @Size(min = 4)
    var username: String,

    @Size(min = 8)
    var password: String? = "",

    @Size(min = 8)
    @JsonProperty("repeated_password")
    var repeatedPassword: String? = "",

    )
