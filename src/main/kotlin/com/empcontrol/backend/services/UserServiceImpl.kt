package com.empcontrol.backend.services

import com.empcontrol.backend.domain.Role
import com.empcontrol.backend.domain.User
import com.empcontrol.backend.enums.UserStatus
import com.empcontrol.backend.exception.ObjectNotFoundException
import com.empcontrol.backend.model.UserRequest
import com.empcontrol.backend.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val roleService: RoleService
): UserService {
    override fun findAll(pageable: Pageable): Page<User> {
        return userRepository.findAll(pageable)
    }

    override fun findOneById(userId: Long): Optional<User> {
        return userRepository.findById(userId)
    }

    override fun findOneByUsername(username: String): Optional<User> {
        return userRepository.findByUsername(username)
    }

    override fun registerOne(newUser: UserRequest): User {
        val defaultRole: Role = roleService.findDefaultRole().orElseThrow{ ObjectNotFoundException("Default role not found.") }
        val user = User(
            name = newUser.name,
            username = newUser.username,
            role = defaultRole,
            status = newUser.status,
            birthdate = newUser.birthdate,
            password = passwordEncoder.encode(newUser.password)
        )
        return userRepository.save(user)
    }

    override fun updateOneById(userId: Long, updatedUser: UserRequest): User {
        val roleDB: Role = roleService.findByName(updatedUser.role).orElseThrow{ ObjectNotFoundException("Default role not found.") }
        val userFromDB: User = userRepository.findById(userId)
            .orElseThrow{ ObjectNotFoundException("Employee with id $userId not found.") }

        userFromDB.name = updatedUser.name
        userFromDB.birthdate = updatedUser.birthdate
        userFromDB.role = roleDB

        return userRepository.save(userFromDB)
    }

    override fun disableEmployeeById(userId: Long): User {
        val userFromDB: User = userRepository.findById(userId)
            .orElseThrow{ ObjectNotFoundException("Employee with id $userId not found.") }

        userFromDB.status = UserStatus.INACTIVE

        return userRepository.save(userFromDB)
    }


}