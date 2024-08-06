package com.empcontrol.backend.services

import com.empcontrol.backend.domain.Customer
import com.empcontrol.backend.model.CustomerRequest
import com.empcontrol.backend.model.LoginRequest
import com.empcontrol.backend.model.LoginResponse
import com.empcontrol.backend.repository.CustomerRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureAlgorithm
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository,
    private val passwordEncoder: PasswordEncoder
):CustomerService {
    override fun registerOneCostumer(newCustomer: CustomerRequest): Customer {
        validatePassword(newCustomer)
        val newCostumer = Customer(
            name = newCustomer.name,
            password = passwordEncoder.encode(newCustomer.password),
            username = newCustomer.username,
            role = newCustomer.role
        )
        return customerRepository.save(newCostumer)
    }

    override fun login(loginRequest: LoginRequest): Optional<Customer> {
        return customerRepository.findByUsername(loginRequest.username)
    }

    override fun validatePassword(newCustomer: CustomerRequest) {
        return
        TODO("Not yet implemented")
    }
}