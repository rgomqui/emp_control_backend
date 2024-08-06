package com.empcontrol.backend.model

import com.empcontrol.backend.enums.EmployeeRoles
import com.empcontrol.backend.enums.EmployeeStatus
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.time.LocalDate
import java.util.*

data class EmployeeRequest(
    var status: EmployeeStatus,

    var role: EmployeeRoles,

    var name: String,

    var surname: String,

    var birthdate: LocalDate,

    var audit: Audit

)
