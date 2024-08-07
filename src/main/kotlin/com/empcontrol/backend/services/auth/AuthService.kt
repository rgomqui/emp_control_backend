package com.empcontrol.backend.services.auth

import com.empcontrol.backend.domain.Employee
import com.empcontrol.backend.model.EmployeeRequest
import com.empcontrol.backend.model.EmployeeResponse
import com.empcontrol.backend.model.LoginRequest
import com.empcontrol.backend.model.LoginResponse

interface AuthService {
    abstract fun registerOne(newEmployee: EmployeeRequest): EmployeeResponse
    abstract fun login(loginRequest: LoginRequest): LoginResponse
    abstract fun generateExtraClaims(employee: Employee?): Map<String, Any>
    abstract fun validatePassword(newEmployee: EmployeeRequest)
    abstract fun validateToken(jwt: String): Boolean
    abstract fun findLoggedInEmployee(): Employee

}