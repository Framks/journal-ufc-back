package com.ufc.jornal.context.teacher

import com.ufc.jornal.domain.Teacher

data class ContextTeacherFindList(
    val page: Int,
    val size: Int,
    val list: List<Teacher>? = null,
){
    fun addList(list: List<Teacher>) = copy(list = list)
}
