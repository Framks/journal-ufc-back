package com.ufc.jornal.context.teacher

import com.ufc.jornal.domain.Teacher
import com.ufc.jornal.rest.request.teacher.TeacherPutRequest
import java.time.LocalDateTime

data class ContextTeacherUpdate(
    val code: String,
    val request: TeacherPutRequest,
    val teacher: Teacher? = null,
    val updateAt: LocalDateTime = LocalDateTime.now()
){
    fun addTeacher(teacher: Teacher) = copy(teacher = teacher)
}
