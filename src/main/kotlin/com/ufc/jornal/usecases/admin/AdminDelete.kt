package com.ufc.jornal.usecases.admin

import com.ufc.jornal.service.AdminService
import org.springframework.stereotype.Service

@Service
class AdminDelete(
    private val adminService: AdminService,
) {
    fun delete(code: String){
        adminService.delete(code)
    }
}