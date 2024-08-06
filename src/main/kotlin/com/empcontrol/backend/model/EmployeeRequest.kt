package com.empcontrol.backend.model

import com.empcontrol.backend.enums.EmployeeRoles
import com.empcontrol.backend.enums.EmployeeStatus
import java.time.LocalDate

data class EmployeeRequest(
    var status: EmployeeStatus,

    var role: EmployeeRoles,

    var name: String,

    var surname: String,

    var birthdate: LocalDate,

    )
