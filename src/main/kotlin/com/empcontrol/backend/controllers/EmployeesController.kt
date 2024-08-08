package com.empcontrol.backend.controllers

import com.empcontrol.backend.domain.Employee
import com.empcontrol.backend.model.EmployeeRequest
import com.empcontrol.backend.services.EmployeeService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/employees")
class EmployeesController(private val employeeService: EmployeeService) {

    @GetMapping
    fun getEmployees(pageable: Pageable): ResponseEntity<Page<Employee>> {
       val employeesPage: Page<Employee> = employeeService.findAll(pageable)

        if(employeesPage.hasContent()) return ResponseEntity.ok(employeesPage)

        return ResponseEntity.notFound().build()
    }

    @GetMapping("/{employeeId}")
    fun getEmployeeById(@PathVariable employeeId: Long): ResponseEntity<Employee> {
        val employee: Optional<Employee> = employeeService.findOneById(employeeId)

        if(employee.isPresent) return ResponseEntity.ok(employee.get())

        return ResponseEntity.notFound().build()
    }

    @PutMapping("/{employeeId}")
    fun updateEmployee(
        @PathVariable employeeId: Long,
        @RequestBody @Valid updatedEmployee: EmployeeRequest
    ): ResponseEntity<Employee> {
        val employee: Employee = employeeService.updateOneById(employeeId, updatedEmployee)

        return ResponseEntity.ok(employee)
    }
    @PutMapping("/{employeeId}/disable")
    fun updateStatusById(
        @PathVariable employeeId: Long): ResponseEntity<Employee> {
        val employee: Employee = employeeService.disableEmployeeById(employeeId)

        return ResponseEntity.ok(employee)
    }

}