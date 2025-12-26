package com.ufc.jornal.context.student

import com.ufc.jornal.domain.Student
import com.ufc.jornal.rest.request.student.StudentRequest

data class CreateStudentContext(
    val request: StudentRequest,
    val student: Student? = null
) {
    fun addStudent(student: Student) = copy(student = student)
}
