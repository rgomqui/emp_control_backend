package com.empcontrol.backend.services.auth

import com.empcontrol.backend.domain.Employee
import com.empcontrol.backend.exception.ObjectNotFoundException
import com.empcontrol.backend.model.EmployeeRequest
import com.empcontrol.backend.model.EmployeeResponse
import com.empcontrol.backend.model.LoginRequest
import com.empcontrol.backend.model.LoginResponse
import com.empcontrol.backend.services.EmployeeService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.concurrent.thread

@Service
class AuthServiceImpl(
    private val employeeService: EmployeeService,
    private val jwtService: JWTService,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder
    ): AuthService {

    override fun registerOne(newEmployee: EmployeeRequest): EmployeeResponse {
        val savedEmployee = employeeService.registerOne(newEmployee)

        return EmployeeResponse(
            id = savedEmployee.id!!,
            name = savedEmployee.name,
            username = savedEmployee.username,
            role = savedEmployee.role,
            status = savedEmployee.status,
            birthdate = savedEmployee.birthdate
        )
    }

    override fun login(loginRequest: LoginRequest): LoginResponse {

        val authentication = UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        authenticationManager.authenticate(authentication)

        val user: UserDetails = employeeService.findOneByUsername(loginRequest.username).get()

        if(user.username.isEmpty()) throw ObjectNotFoundException("Employee with id ${loginRequest.username} not found.")
        return LoginResponse(
            jwt = jwtService.generateToken(user, generateExtraClaims(user as Employee))
        )
    }

    override fun generateExtraClaims(employee: Employee?): Map<String, Any> {
        var extraClaims: Map<String, Any> = hashMapOf(
            "role" to employee!!.role.name,
            "name" to employee.name,
            "authorities" to employee.authorities,
            "status" to employee.status,
        )

        return extraClaims
    }

    override fun validatePassword(newEmployee: EmployeeRequest) {
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

    override fun findLoggedInEmployee(): Employee {
        val auth = SecurityContextHolder.getContext().authentication

        if(auth is UsernamePasswordAuthenticationToken ) {
            val username = auth.principal.toString()
            return employeeService.findOneByUsername(username).orElseThrow { ObjectNotFoundException("User $username not found. ") }
        }

        throw ObjectNotFoundException("User not logged in. ")
    }
}