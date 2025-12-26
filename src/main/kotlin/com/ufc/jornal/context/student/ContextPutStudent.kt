package com.ufc.jornal.context.student

import com.ufc.jornal.domain.Student
import com.ufc.jornal.rest.request.student.StudentPutRequest
import java.time.LocalDateTime

data class ContextPutStudent(
    val code: String,
    val request: StudentPutRequest,
    val student: Student? = null,
    val updatedAt: LocalDateTime = LocalDateTime.now()
){
    fun addSudent(student: Student) = copy( student = student)
}
