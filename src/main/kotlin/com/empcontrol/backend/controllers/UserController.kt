package com.empcontrol.backend.controllers

import com.empcontrol.backend.domain.User
import com.empcontrol.backend.model.UserRequest
import com.empcontrol.backend.services.UserService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.jvm.Throws

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    fun getEmployees(pageable: Pageable): ResponseEntity<Page<User>> {
       val employeesPage: Page<User> = userService.findAll(pageable)

        if(employeesPage.hasContent()) return ResponseEntity.ok(employeesPage)

        return ResponseEntity.notFound().build()
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{employeeId}")
    fun getEmployeeById(@PathVariable employeeId: Long): ResponseEntity<User> {
        val user: Optional<User> = userService.findOneById(employeeId)

        if(user.isPresent) return ResponseEntity.ok(user.get())

        return ResponseEntity.notFound().build()
    }

//    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PutMapping("/{employeeId}")
    fun updateEmployee(
        @PathVariable employeeId: Long,
        @RequestBody @Valid updatedEmployee: UserRequest
    ): ResponseEntity<User> {
        val user: User = userService.updateOneById(employeeId, updatedEmployee)

        return ResponseEntity.ok(user)
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{employeeId}/disable")
    fun updateStatusById(
        @PathVariable employeeId: Long): ResponseEntity<User> {
        val user: User = userService.disableEmployeeById(employeeId)

        return ResponseEntity.ok(user)
    }

}