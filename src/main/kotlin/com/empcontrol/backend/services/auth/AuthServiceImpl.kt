package com.empcontrol.backend.services.auth

import com.empcontrol.backend.domain.Customer
import com.empcontrol.backend.exception.EmployeeNotFoundException
import com.empcontrol.backend.model.CustomerRequest
import com.empcontrol.backend.model.CustomerResponse
import com.empcontrol.backend.model.LoginRequest
import com.empcontrol.backend.model.LoginResponse
import com.empcontrol.backend.services.CustomerService
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl(
    private val customerService: CustomerService,
    private val jwtService: JWTService
): AuthService {
    override fun registerOneCostumer(newCustomer: CustomerRequest): CustomerResponse {
        val savedCustomer = customerService.registerOneCostumer(newCustomer)
        return CustomerResponse(
            id = savedCustomer.id!!,
            name = savedCustomer.name,
            username = savedCustomer.username,
            role = savedCustomer.role
        )
    }

    override fun login(loginRequest: LoginRequest): LoginResponse {
        val customerDB = customerService.login(loginRequest)
            .orElseThrow{ EmployeeNotFoundException("Employee with id ${loginRequest.username} not found.") }

        if(customerDB.name.isEmpty()) throw EmployeeNotFoundException("Employee with id ${loginRequest.username} not found.")
        return LoginResponse(
            username = customerDB.username,
            jwt = jwtService.generateToken(customerDB, generateExtraClaims(customerDB))
        )
    }

    override fun generateExtraClaims(customerDB: Customer?): Map<String, Any> {
        var extraClaims: Map<String, Any> = hashMapOf(
            "role" to customerDB!!.role.name,
            "name" to customerDB.name,
            "authorities" to customerDB.authorities,
        )

        return extraClaims
    }
}