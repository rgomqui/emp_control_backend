package com.empcontrol.backend.domain

import jakarta.persistence.*

@Entity
@SequenceGenerator(
    name = "operation_seq",
    sequenceName = "operation_sequence",
    initialValue = 10,
    allocationSize = 1
)
class Operation(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "operation_seq")
    var id: Long? = 0,

    var name: String,

    var path: String,

    var httpMethod: String,

    var permitAll: Boolean,

    @ManyToOne
    @JoinColumn(name = "module_id")
    var module: Module
    )
