package com.empcontrol.backend.services.auth

import com.empcontrol.backend.domain.User
import com.empcontrol.backend.model.UserRequest
import com.empcontrol.backend.model.UserResponse
import com.empcontrol.backend.model.LoginRequest
import com.empcontrol.backend.model.LoginResponse

interface AuthService {
    abstract fun registerOne(newUser: UserRequest): UserResponse
    abstract fun login(loginRequest: LoginRequest): LoginResponse
    abstract fun generateExtraClaims(user: User?): Map<String, Any>
    abstract fun validatePassword(newEmployee: UserRequest)
    abstract fun validateToken(jwt: String): Boolean
    abstract fun findLoggedInEmployee(): User

}