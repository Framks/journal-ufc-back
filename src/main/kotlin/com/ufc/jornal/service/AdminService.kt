package com.ufc.jornal.service

import com.ufc.jornal.domain.Admin
import com.ufc.jornal.mapper.AdminMapper
import com.ufc.jornal.repository.AdminRepository
import com.ufc.jornal.rest.request.admin.AdminPutRequest
import com.ufc.jornal.rest.request.admin.AdminRequest
import java.time.LocalDateTime
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val adminRepository: AdminRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    fun create(admin: AdminRequest) =
        AdminMapper.toEntity(admin)

    fun save(admin: Admin) =
        adminRepository.save(admin)

    fun findAll(page: Int, size: Int): List<Admin> {
        val pageable = PageRequest.of(page, size)
        return adminRepository.findAll(pageable).content
    }

    fun findByCode(code: String) =
        adminRepository.findByCode(code)

    fun delete(code: String) =
        adminRepository.deleteByCode(code)

    fun update(admin: Admin, request: AdminPutRequest, updateAt: LocalDateTime): Admin {

        request.name?.takeIf { it.isNotBlank() }
            ?.let { admin.name = it }

        request.email?.takeIf { it.isNotBlank() }
            ?.let { admin.email = it }

        request.password?.takeIf { it.isNotBlank() }
            ?.let { admin.password = passwordEncoder.encode(it) }

        admin.updatedAt = updateAt

        return admin
    }

    fun encriptPassword(admin: Admin): Admin {
        admin.password = passwordEncoder.encode(admin.password)
        return admin
    }
}