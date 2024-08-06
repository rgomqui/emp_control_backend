package com.empcontrol.backend.services

import com.empcontrol.backend.domain.Employee
import com.empcontrol.backend.enums.EmployeeStatus
import com.empcontrol.backend.model.EmployeeRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.Optional

interface EmployeeService {
    abstract fun findAll(pageable: Pageable): Page<Employee>
    abstract fun findOneById(employeeId: Long): Optional<Employee>
    abstract fun createOne(newEmployee: EmployeeRequest): Employee
    abstract fun updateOneById(employeeId: String, updatedEmployee: EmployeeRequest): Employee
    abstract fun disableEmployeeById(employeeId: String): Employee
}