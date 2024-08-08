package com.empcontrol.backend.domain

import com.empcontrol.backend.enums.EmployeeRoles
import com.empcontrol.backend.enums.EmployeeStatus
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate

@Entity
@Table(name = "employee")
@SequenceGenerator(
    name = "customer_seq",
    sequenceName = "customer_sequence",
    initialValue = 1000,
    allocationSize = 1
)
class Employee (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    var id: Long? = 0,

    @Enumerated(EnumType.STRING)
    var status: EmployeeStatus,

    @Enumerated(EnumType.STRING)
    var role: EmployeeRoles,

    var name: String,

    var birthdate: LocalDate,

    @Column(unique = true)
    private var username: String,

    @JsonIgnore
    private var password: String,

    ): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return role?.permissions?.asSequence()
            ?.map { it.name }
            ?.map { SimpleGrantedAuthority(it) }
            ?.toMutableList() ?: mutableListOf()
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }
}