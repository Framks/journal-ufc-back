package com.ufc.jornal.mapper

import com.ufc.jornal.domain.Student
import com.ufc.jornal.rest.request.student.StudentRequest
import com.ufc.jornal.rest.response.student.StudentListResponse
import com.ufc.jornal.rest.response.student.StudentResponse

object StudentMapper {

    fun toEntity(request: StudentRequest) =
        Student(
            name = request.name,
            username = request.username,
            email = request.email,
            password = request.password,
            educationLevel = request.educationLevel,
            isScholarship = request.isScholarship
        )

    fun toResponse(student: Student) =
        StudentResponse(
            code = student.code,
            name = student.name,
            email = student.email,
            username = student.username,
            educationLevel = student.educationLevel,
            isScholarship = student.isScholarship
        )

    fun toListResponse(size: Int, page: Int, students: List<Student>) =
        StudentListResponse(
            size = size,
            page = page,
            students = students.map { toResponse(it) }
        )
}
