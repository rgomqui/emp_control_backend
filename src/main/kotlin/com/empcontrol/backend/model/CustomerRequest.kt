package com.empcontrol.backend.model

import com.empcontrol.backend.enums.EmployeeRoles
import com.empcontrol.backend.enums.EmployeeStatus
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import jakarta.validation.constraints.Size
import java.io.Serializable
import java.time.LocalDate

data class CustomerRequest(
    var id: Long? = 0,

    @Size(min = 4)
    var username: String,

    var name: String,

    @Size(min = 8)
    var password: String,

    @Size(min = 8)
    @JsonProperty("repeated_password")
    var repeatedPassword: String,

    var role: EmployeeRoles
    )
