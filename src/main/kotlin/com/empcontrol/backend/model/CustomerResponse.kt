package com.empcontrol.backend.model

import com.empcontrol.backend.enums.EmployeeRoles
import com.empcontrol.backend.enums.EmployeeStatus
import jakarta.persistence.*
import java.time.LocalDate

data class CustomerResponse(
    private var id: Long,

    private var username: String,

    private var name: String,

    private var role: EmployeeRoles
    )
