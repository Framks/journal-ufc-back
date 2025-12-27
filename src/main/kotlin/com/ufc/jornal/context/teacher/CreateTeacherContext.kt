package com.ufc.jornal.context.teacher

import com.ufc.jornal.domain.Teacher
import com.ufc.jornal.rest.request.teacher.TeacherRequest

data class CreateTeacherContext(
    val request: TeacherRequest,
    val teacher: Teacher? = null
){
    fun addTeacher(teacher: Teacher) = copy(teacher = teacher)
}
