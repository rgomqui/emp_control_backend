package com.empcontrol.backend.model

import com.empcontrol.backend.enums.UserRolesEnum
import com.empcontrol.backend.enums.UserStatus
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class UserRequest(
    var status: UserStatus,

    var role: String,

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
