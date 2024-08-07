package com.empcontrol.backend.controllers

import com.empcontrol.backend.domain.Employee
import com.empcontrol.backend.model.EmployeeRequest
import com.empcontrol.backend.model.EmployeeResponse
import com.empcontrol.backend.model.LoginRequest
import com.empcontrol.backend.model.LoginResponse
import com.empcontrol.backend.services.auth.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @PostMapping
    fun registerOneCustomer(@RequestBody @Valid newEmployee: EmployeeRequest): ResponseEntity<EmployeeResponse> {
        val savedCustomer = authService.registerOne(newEmployee)

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer)
    }

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

    @GetMapping("/profile")
    fun getProfile(): ResponseEntity<Employee>{
        val employee = authService.findLoggedInEmployee()
        return ResponseEntity.ok(employee)
    }
}