package com.empcontrol.backend.controllers

import com.empcontrol.backend.model.EmployeeRequest
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/employees")
class EmployeesController {

    @GetMapping("/")
    fun getEmployees(): String {
        return "get employees"
    }

    @GetMapping("/:id")
    fun getEmployeebyId(id: String): String {
        return "get employees by id $id"
    }

    @PostMapping("/")
    fun addEmployee( employee: EmployeeRequest): String {
        return "add employees \n$employee"
    }

    @PutMapping("/:id")
    fun updateEmployee(id: String, employee: EmployeeRequest): String {
        return "put employee"
    }

    @DeleteMapping("/:id")
    fun addEmployees(id: String): String {
        return "delete employee with $id"
    }
}