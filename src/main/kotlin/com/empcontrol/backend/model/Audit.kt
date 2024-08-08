package com.empcontrol.backend.model

import java.util.*

data class Audit(
    val createdBy: String,
    val createdAt: Date,
    val updatedBy: String?,
    val updatedAt: Date?,
    val deletedBy: String?,
    val deletedAt: Date?
)
