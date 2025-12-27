package com.ufc.jornal.repository

import com.ufc.jornal.domain.Admin
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdminRepository: JpaRepository<Admin, Long> {
    fun findByCode(code: String): Admin

    fun deleteByCode(code: String)
}