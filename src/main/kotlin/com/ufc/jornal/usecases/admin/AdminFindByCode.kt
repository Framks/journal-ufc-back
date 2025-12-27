package com.ufc.jornal.usecases.admin

import com.ufc.jornal.context.admin.ContextAdminFindByCode
import com.ufc.jornal.mapper.AdminMapper
import com.ufc.jornal.service.AdminService
import org.springframework.stereotype.Service

@Service
class AdminFindByCode(
    private val adminService: AdminService,
) {

    fun find(code: String) =
        ContextAdminFindByCode(code)
            .let { fechAdmin(it) }
            .let { AdminMapper.toResponse(it.admin!!) }

    private fun fechAdmin(context: ContextAdminFindByCode) =
        context
            .let { adminService.findByCode(it.code) }
            .let { context.addAdmin(it) }
}