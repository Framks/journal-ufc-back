package com.ufc.jornal.repository

import com.ufc.jornal.domain.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository : JpaRepository<Student, Long> {

    fun findByCode(code: String): Student

    fun deleteByCode(code: String)
}
