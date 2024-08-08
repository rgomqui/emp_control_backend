package com.empcontrol.backend.repository

import com.empcontrol.backend.domain.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository: JpaRepository<Role, Long> {
    fun findByName(defaultRole: String): Optional<Role>

}
