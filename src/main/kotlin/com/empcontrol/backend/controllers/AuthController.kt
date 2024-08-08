package com.empcontrol.backend.controllers

import com.empcontrol.backend.domain.User
import com.empcontrol.backend.model.UserRequest
import com.empcontrol.backend.model.UserResponse
import com.empcontrol.backend.model.LoginRequest
import com.empcontrol.backend.model.LoginResponse
import com.empcontrol.backend.services.auth.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController(
    val authService: AuthService
) {


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    fun registerOneCustomer(@RequestBody @Valid newEmployee: UserRequest): ResponseEntity<UserResponse> {
        val savedCustomer = authService.registerOne(newEmployee)

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer)
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/validate")
    fun validateToken(@RequestParam @Valid jwt: String): ResponseEntity<Boolean>{
        val isValidToken = authService.validateToken(jwt)

        return ResponseEntity.ok(isValidToken)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        val savedCustomer = authService.login(loginRequest)

        return ResponseEntity.ok(savedCustomer)
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/profile")
    fun getProfile(): ResponseEntity<User>{
        val employee = authService.findLoggedInEmployee()
        return ResponseEntity.ok(employee)
    }
}