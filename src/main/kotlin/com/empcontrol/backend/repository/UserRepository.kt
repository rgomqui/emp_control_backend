package com.empcontrol.backend.repository

import com.empcontrol.backend.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository: JpaRepository<User, Long> {
    abstract fun findByUsername(it: String?): Optional<User>
}