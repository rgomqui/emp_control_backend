package com.empcontrol.backend.model

import com.empcontrol.backend.domain.Role
import com.empcontrol.backend.enums.UserRolesEnum
import com.empcontrol.backend.enums.UserStatus
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class UserResponse(
    var id: Long,

    var status: UserStatus,

    var role: Role,

    var name: String,

    var birthdate: LocalDate,

    @Size(min = 4)
    var username: String,

    )
