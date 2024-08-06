package com.empcontrol.backend.repository

import com.empcontrol.backend.domain.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository: JpaRepository<Employee, Long> {
}