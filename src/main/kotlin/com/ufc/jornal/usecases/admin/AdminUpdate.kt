package com.ufc.jornal.usecases.admin

import com.ufc.jornal.context.admin.ContextAdminUpdate
import com.ufc.jornal.mapper.AdminMapper
import com.ufc.jornal.rest.request.admin.AdminPutRequest
import com.ufc.jornal.service.AdminService
import org.springframework.stereotype.Service

@Service
class AdminUpdate(
    private val adminService: AdminService,
) {

    fun update(code: String, request: AdminPutRequest) =
        ContextAdminUpdate(code, request)
            .let { fechAdmin(it) }
            .let { updateInfo(it) }
            .let { save(it) }
            .let { AdminMapper.toResponse(it.admin!!) }

    private fun fechAdmin(context: ContextAdminUpdate) =
        context
            .let { adminService.findByCode(it.code) }
            .let { context.addAdmin(it) }

    private fun updateInfo(context: ContextAdminUpdate) =
        adminService.update(
            admin = context.admin!!,
            request = context.request,
            updateAt = context.updateAt
        ).let { context.addAdmin(it) }

    private fun save(context: ContextAdminUpdate) =
        context
            .let { adminService.save(it.admin!!) }
            .let { context.addAdmin(it) }
}