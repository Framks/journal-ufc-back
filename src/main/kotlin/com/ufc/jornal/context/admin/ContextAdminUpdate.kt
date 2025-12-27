package com.ufc.jornal.context.admin

import com.ufc.jornal.domain.Admin
import com.ufc.jornal.rest.request.admin.AdminPutRequest
import java.time.LocalDateTime

data class ContextAdminUpdate(
    val code: String,
    val request: AdminPutRequest,
    val admin: Admin? = null,
    val updateAt: LocalDateTime = LocalDateTime.now()
){
    fun addAdmin(admin: Admin) = copy(admin = admin)
}
