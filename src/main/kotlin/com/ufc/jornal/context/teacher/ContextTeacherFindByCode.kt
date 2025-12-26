package com.ufc.jornal.context.teacher

import com.ufc.jornal.domain.Teacher

data class ContextTeacherFindByCode(
    val code: String,
    val teacher: Teacher? = null
){
    fun addTeacher(teacher: Teacher) = copy(teacher = teacher)
}
