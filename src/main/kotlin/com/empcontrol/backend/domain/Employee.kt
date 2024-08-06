package com.empcontrol.backend.domain

import com.empcontrol.backend.enums.EmployeeRoles
import com.empcontrol.backend.enums.EmployeeStatus
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "employee")
class Employee (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = 0,

    @Enumerated(EnumType.STRING)
    var status: EmployeeStatus,

    @Enumerated(EnumType.STRING)
    var role: EmployeeRoles,

    var name: String,

    var surname: String,

    var birthdate: LocalDate,

    )