package com.ufc.jornal.usecases.admin

import com.ufc.jornal.context.admin.ContextAdminFindList
import com.ufc.jornal.mapper.AdminMapper
import com.ufc.jornal.service.AdminService
import org.springframework.stereotype.Service

@Service
class AdminList(
    private val adminService: AdminService,
){

    fun find(size: Int, page: Int) =
        ContextAdminFindList(page = page, size = size)
            .let { fechList(it) }
            .let { AdminMapper.toAdminListResponse(it.list!!, it.page, it.size) }

    private fun fechList(context: ContextAdminFindList) =
        context
            .let { adminService.findAll(it.page, it.size) }
            .let { context.addList(it) }
}