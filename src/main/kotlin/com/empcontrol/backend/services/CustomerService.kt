package com.empcontrol.backend.services

import com.empcontrol.backend.domain.Customer
import com.empcontrol.backend.model.CustomerRequest
import com.empcontrol.backend.model.LoginRequest
import com.empcontrol.backend.model.LoginResponse
import java.util.*

interface CustomerService {
    abstract fun registerOneCostumer(newCustomer: CustomerRequest): Customer
    abstract fun login(loginRequest: LoginRequest): Optional<Customer>
    abstract fun validatePassword(newCustomer: CustomerRequest)

}