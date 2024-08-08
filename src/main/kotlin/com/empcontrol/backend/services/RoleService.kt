package com.empcontrol.backend.services

import com.empcontrol.backend.domain.Role
import org.springframework.stereotype.Service
import java.util.Optional

interface RoleService {
    abstract fun findDefaultRole(): Optional<Role>
    abstract fun findByName(roleName: String): Optional<Role>
}