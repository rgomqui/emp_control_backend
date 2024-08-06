package com.empcontrol.backend.services.auth

import com.empcontrol.backend.domain.Customer
import com.empcontrol.backend.model.CustomerRequest
import com.empcontrol.backend.model.CustomerResponse
import com.empcontrol.backend.model.LoginRequest
import com.empcontrol.backend.model.LoginResponse

interface AuthService {
    abstract fun registerOneCostumer(newCustomer: CustomerRequest): CustomerResponse
    abstract fun login(loginRequest: LoginRequest): LoginResponse
    abstract fun generateExtraClaims(customerDB: Customer?): Map<String, Any>
}