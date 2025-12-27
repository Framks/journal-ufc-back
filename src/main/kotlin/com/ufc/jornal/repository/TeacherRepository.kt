package com.ufc.jornal.repository

import com.ufc.jornal.domain.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepository: JpaRepository<Teacher, Long> {
    fun findByCode(code: String): Teacher?

    fun deleteByCode(code: String)
}