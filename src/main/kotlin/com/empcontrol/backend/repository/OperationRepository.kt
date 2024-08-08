package com.empcontrol.backend.repository

import com.empcontrol.backend.domain.Operation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OperationRepository: JpaRepository<Operation ,Long>{
    @Query("SELECT o FROM Operation o where o.permitAll = true")
    fun findByPublicAccess(): List<Operation>
}
