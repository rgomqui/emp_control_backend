package com.empcontrol.backend.repository

import com.empcontrol.backend.domain.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmployeeRepository: JpaRepository<Employee, Long> {
    abstract fun findByUsername(it: String?): Optional<Employee>
}