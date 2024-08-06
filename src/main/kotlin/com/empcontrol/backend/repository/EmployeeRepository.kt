package com.empcontrol.backend.repository

import com.empcontrol.backend.domain.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository: JpaRepository<Employee, Long> {
}