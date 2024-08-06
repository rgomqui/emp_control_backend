package com.empcontrol.backend.domain

import com.empcontrol.backend.enums.EmployeeRoles
import com.empcontrol.backend.enums.EmployeeStatus
import com.empcontrol.backend.model.Audit
import jakarta.persistence.*
import org.hibernate.annotations.Type
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "employees")
class Employee (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long,

    @Enumerated(EnumType.STRING)
    var status: EmployeeStatus,

    @Enumerated(EnumType.STRING)
    var role: EmployeeRoles,

    var name: String,

    var surname: String,

    var birthdate: LocalDate,

//    var audit: Audit

)