package com.empcontrol.backend.domain

import jakarta.persistence.*

@Entity
@SequenceGenerator(
    name = "module_seq",
    sequenceName = "module_sequence",
    initialValue = 10,
    allocationSize = 1
)
class Module(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "module_seq")
    var id: Long? = 0,

    var name: String,

    var basePath: String
)