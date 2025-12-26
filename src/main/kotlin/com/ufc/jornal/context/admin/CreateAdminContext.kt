package com.ufc.jornal.context.admin

import com.ufc.jornal.domain.Admin
import com.ufc.jornal.rest.request.admin.AdminRequest

data class CreateAdminContext(
    val request: AdminRequest,
    val admin: Admin? = null
){
    fun addTeacher(admin: Admin) = copy(admin = admin)
}
