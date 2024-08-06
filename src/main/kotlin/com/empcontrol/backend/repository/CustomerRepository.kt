package com.empcontrol.backend.repository

import com.empcontrol.backend.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface CustomerRepository: JpaRepository<Customer, Long> {
    abstract fun findByUsername(it: String?): Optional<Customer>
}