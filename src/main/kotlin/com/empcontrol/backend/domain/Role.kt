package com.empcontrol.backend.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "role")
@SequenceGenerator(
    name = "role_seq",
    sequenceName = "role_sequence",
    initialValue = 10,
    allocationSize = 1
)
class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_seq")
    var id: Long? = 0,

    var name: String,

    @JsonIgnore
    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER )
    var permisions: List<RoleOperation>
)