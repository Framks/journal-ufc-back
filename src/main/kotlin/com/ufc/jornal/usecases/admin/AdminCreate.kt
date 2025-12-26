package com.ufc.jornal.usecases.admin

import com.ufc.jornal.context.admin.CreateAdminContext
import com.ufc.jornal.mapper.AdminMapper
import com.ufc.jornal.rest.request.admin.AdminRequest
import com.ufc.jornal.service.AdminService
import org.springframework.stereotype.Service

@Service
class AdminCreate(
    private val adminService: AdminService,
){

    fun create(request: AdminRequest)
    = CreateAdminContext(request)
        .let { createAdmin(it) }
        .let { save(it) }
        .let { AdminMapper.toResponse(it.admin!!) }

    private fun createAdmin(context: CreateAdminContext) =
        context
            .let { adminService.create(it.request) }
            .let{ context.addTeacher(it) }

    private fun save(context: CreateAdminContext) =
        context
            .let { adminService.save(it.admin!!) }
            .let { context }
}