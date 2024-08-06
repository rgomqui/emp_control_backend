package com.empcontrol.backend.domain

import com.empcontrol.backend.enums.EmployeeRoles
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "\"user\"")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private var id: Long? = 0,

    private var username: String,

    private var name: String,

    private var password: String,

    @Enumerated(EnumType.STRING)
    private var role: EmployeeRoles
): UserDetails
{

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