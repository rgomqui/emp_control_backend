package com.empcontrol.backend.domain

import com.empcontrol.backend.enums.UserRolesEnum
import com.empcontrol.backend.enums.UserStatus
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDate

@Entity
@Table(name = "\"user\"")
@SequenceGenerator(
    name = "user_seq",
    sequenceName = "user_sequence",
    initialValue = 1000,
    allocationSize = 1
)
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    var id: Long? = 0,

    @Enumerated(EnumType.STRING)
    var status: UserStatus,

    @ManyToOne
    @JoinColumn(name = "role_id")
    var role: Role,

    var name: String,

    var birthdate: LocalDate,

    @Column(unique = true)
    private var username: String,

    @JsonIgnore
    private var password: String,

    ): UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = role.permisions.asSequence()
            ?.map { it.operation.name }
            ?.map { SimpleGrantedAuthority(it) }
            ?.toMutableList() ?: mutableListOf()
        authorities.add(SimpleGrantedAuthority("ROLE_${role.name}"))
        return authorities
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return username
    }
}