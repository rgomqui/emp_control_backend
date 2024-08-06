package com.empcontrol.backend.services

import com.empcontrol.backend.domain.Employee
import com.empcontrol.backend.model.EmployeeRequest
import com.empcontrol.backend.repository.EmployeeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeServiceImpl(
    private val employeeRepository: EmployeeRepository
): EmployeeService{
    override fun findAll(pageable: Pageable): Page<Employee> {
        return employeeRepository.findAll(pageable)
    }

    override fun findOneById(employeeId: Long): Optional<Employee> {
        return employeeRepository.findById(employeeId)
    }

    override fun createOne(newEmployee: EmployeeRequest): Employee {
        TODO("Not yet implemented")
    }

    override fun updateOneById(employeeId: String, updatedEmployee: EmployeeRequest): Employee {
        TODO("Not yet implemented")
    }

    override fun disableEmployeeById(employeeId: String): Employee {
        TODO("Not yet implemented")
    }


}