package com.empcontrol.backend.services

import com.empcontrol.backend.domain.Role
import com.empcontrol.backend.repository.RoleRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class RoleServiceImpl(
    private val roleRepository: RoleRepository,
    @Value("\${security.default.role}")
    private val defaultRole: String
): RoleService {
    override fun findDefaultRole(): Optional<Role> {
        return roleRepository.findByName(defaultRole)
    }

    override fun findByName(roleName: String): Optional<Role> {
        return roleRepository.findByName(roleName)
    }
}