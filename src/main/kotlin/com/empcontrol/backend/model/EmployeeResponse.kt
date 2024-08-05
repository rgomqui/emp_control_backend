package com.empcontrol.backend.model

import java.util.*

data class EmployeeResponse(
    val id: UUID,

    val name: String,

    val surname: String,

    val birthdate: Date,

    val audit: Audit

)
