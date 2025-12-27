package com.ufc.jornal.context.admin

import com.ufc.jornal.domain.Admin

data class ContextAdminFindList(
    val page: Int,
    val size: Int,
    val list: List<Admin>? = null,
){
    fun addList(list: List<Admin>) = copy(list = list)
}
