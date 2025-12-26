package com.ufc.jornal.mapper

import com.ufc.jornal.domain.Admin
import com.ufc.jornal.rest.request.admin.AdminRequest
import com.ufc.jornal.rest.response.admin.AdminListResponse
import com.ufc.jornal.rest.response.admin.AdminResponse

object AdminMapper {
    fun toResponse(admin: Admin)
            = AdminResponse(
        name = admin.name,
        email = admin.email,
        username = admin.username,
        code = admin.code,
    )

    fun toEntity(
        admin: AdminRequest,
    ) = Admin(
        name = admin.name,
        email = admin.email,
        password = admin.password,
        username = admin.username,
    )

    fun toAdminListResponse(list: List<Admin>, page: Int, size: Int)
            = AdminListResponse(
        page = page,
        size = size,
        teachers = list.map { toResponse(it) }.toList()
    )
}