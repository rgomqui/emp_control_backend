package com.empcontrol.backend.services

import com.empcontrol.backend.domain.Employee
import com.empcontrol.backend.enums.EmployeeStatus
import com.empcontrol.backend.exception.ObjectNotFoundException
import com.empcontrol.backend.model.EmployeeRequest
import com.empcontrol.backend.repository.EmployeeRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployeeServiceImpl(
    private val employeeRepository: EmployeeRepository,
    private val passwordEncoder: PasswordEncoder
): EmployeeService {
    override fun findAll(pageable: Pageable): Page<Employee> {
        return employeeRepository.findAll(pageable)
    }

    override fun findOneById(employeeId: Long): Optional<Employee> {
        return employeeRepository.findById(employeeId)
    }

    override fun findOneByUsername(username: String): Optional<Employee> {
        return employeeRepository.findByUsername(username)
    }

    override fun registerOne(newEmployee: EmployeeRequest): Employee {
        val employee = Employee(
            name = newEmployee.name,
            username = newEmployee.username,
            role = newEmployee.role,
            status = newEmployee.status,
            birthdate = newEmployee.birthdate,
            password = passwordEncoder.encode(newEmployee.password)
        )
        return employeeRepository.save(employee)
    }

    override fun updateOneById(employeeId: Long, updatedEmployee: EmployeeRequest): Employee {
        val employeeFromDB: Employee = employeeRepository.findById(employeeId)
            .orElseThrow{ ObjectNotFoundException("Employee with id $employeeId not found.") }

        employeeFromDB.name = updatedEmployee.name
        employeeFromDB.birthdate = updatedEmployee.birthdate
        employeeFromDB.role = updatedEmployee.role

        return employeeRepository.save(employeeFromDB)
    }

    override fun disableEmployeeById(employeeId: Long): Employee {
        val employeeFromDB: Employee = employeeRepository.findById(employeeId)
            .orElseThrow{ ObjectNotFoundException("Employee with id $employeeId not found.") }

        employeeFromDB.status = EmployeeStatus.INACTIVE

        return employeeRepository.save(employeeFromDB)
    }


}