package com.ufc.jornal.context.admin

import com.ufc.jornal.domain.Admin

data class ContextAdminFindByCode(
    val code: String,
    val admin: Admin? = null
){
    fun addAdmin(admin: Admin) = copy(admin = admin)
}
