package com.empcontrol.backend.domain

import com.empcontrol.backend.enums.EmployeeRoles
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "customer")
@SequenceGenerator(
    name = "customer_seq",
    sequenceName = "customer_sequence",
    initialValue = 1000,
    allocationSize = 1
)
class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    var id: Long? = 0,

    @Column(unique = true)
    private var username: String,

    var name: String,

    private var password: String,

    @Enumerated(EnumType.STRING)
    var role: EmployeeRoles
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