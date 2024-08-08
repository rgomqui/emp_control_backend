package com.empcontrol.backend.model

import com.empcontrol.backend.enums.EmployeeRoles
import com.empcontrol.backend.enums.EmployeeStatus
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class EmployeeResponse(
    var id: Long,

    var status: EmployeeStatus,

    var role: EmployeeRoles,

    var name: String,

    var birthdate: LocalDate,

    @Size(min = 4)
    var username: String,

    )
