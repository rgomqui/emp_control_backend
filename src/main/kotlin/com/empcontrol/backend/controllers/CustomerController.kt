package com.empcontrol.backend.controllers

import com.empcontrol.backend.model.CustomerRequest
import com.empcontrol.backend.model.CustomerResponse
import com.empcontrol.backend.model.LoginRequest
import com.empcontrol.backend.model.LoginResponse
import com.empcontrol.backend.services.auth.AuthService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class CustomerController(
    val authService: AuthService
) {

    @PostMapping
    fun registerOneCustomer(@RequestBody @Valid newCustomer: CustomerRequest): ResponseEntity<CustomerResponse> {
        val savedCustomer = authService.registerOneCostumer(newCustomer)

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer)
    }

    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequest: LoginRequest): ResponseEntity<LoginResponse> {
        val savedCustomer = authService.login(loginRequest)

        return ResponseEntity.ok(savedCustomer)
    }
}