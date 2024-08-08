package com.empcontrol.backend.services

import com.empcontrol.backend.domain.User
import com.empcontrol.backend.model.UserRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

interface UserService {
    abstract fun findAll(pageable: Pageable): Page<User>
    abstract fun findOneById(userId: Long): Optional<User>
    abstract fun findOneByUsername(username: String): Optional<User>
    abstract fun registerOne(newUser: UserRequest): User
    abstract fun updateOneById(userId: Long, updatedUser: UserRequest): User
    abstract fun disableEmployeeById(userId: Long): User
}