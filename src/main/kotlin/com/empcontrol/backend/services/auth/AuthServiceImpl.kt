package com.empcontrol.backend.services.auth

import com.empcontrol.backend.domain.User
import com.empcontrol.backend.exception.ObjectNotFoundException
import com.empcontrol.backend.model.UserRequest
import com.empcontrol.backend.model.UserResponse
import com.empcontrol.backend.model.LoginRequest
import com.empcontrol.backend.model.LoginResponse
import com.empcontrol.backend.services.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val userService: UserService,
    private val jwtService: JWTService,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder
    ): AuthService {

    override fun registerOne(newUser: UserRequest): UserResponse {
        val savedUser= userService.registerOne(newUser)

        return UserResponse(
            id = savedUser.id!!,
            name = savedUser.name,
            username = savedUser.username,
            role = savedUser.role,
            status = savedUser.status,
            birthdate = savedUser.birthdate
        )
    }

    override fun login(loginRequest: LoginRequest): LoginResponse {

        val authentication = UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        authenticationManager.authenticate(authentication)

        val user: UserDetails = userService.findOneByUsername(loginRequest.username).get()

        if(user.username.isEmpty()) throw ObjectNotFoundException("Employee with id ${loginRequest.username} not found.")
        return LoginResponse(
            jwt = jwtService.generateToken(user, generateExtraClaims(user as User))
        )
    }

    override fun generateExtraClaims(user: User?): Map<String, Any> {
        val extraClaims: Map<String, Any> = hashMapOf(
            "role" to user!!.role.name,
            "name" to user.name,
            "authorities" to user.authorities,
            "status" to user.status,
        )

        return extraClaims
    }

    override fun validatePassword(newEmployee: UserRequest) {
        TODO("Not yet implemented")
    }

    override fun validateToken(jwt: String): Boolean {
        return try {
            jwtService.extractUsername(jwt)
            true
        } catch (e: Exception) {
            println( "ERROR: $e")
            false
        }
    }

    override fun findLoggedInEmployee(): User {
        val auth = SecurityContextHolder.getContext().authentication

        if(auth is UsernamePasswordAuthenticationToken ) {
            val username = auth.principal.toString()
            return userService.findOneByUsername(username).orElseThrow { ObjectNotFoundException("User $username not found. ") }
        }

        throw ObjectNotFoundException("User not logged in. ")
    }
}