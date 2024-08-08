package com.empcontrol.backend.domain

import jakarta.persistence.*

@Entity
@SequenceGenerator(
    name = "role_operation_seq",
    sequenceName = "role_operation_sequence",
    initialValue = 100,
    allocationSize = 1
)
class RoleOperation(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_operation_seq")
    var id: Long? = 0,

    @ManyToOne
    @JoinColumn(name = "role_id")
    val role: Role,

    @ManyToOne
    @JoinColumn(name = "operation_id")
    val operation: Operation
)
