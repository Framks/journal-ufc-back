package com.ufc.jornal.usecases.student

import com.ufc.jornal.mapper.StudentMapper
import com.ufc.jornal.rest.response.student.StudentListResponse
import com.ufc.jornal.service.StudentService
import org.springframework.stereotype.Service

@Service
class StudentList(
    private val studentService: StudentService,
){   // ToDo ajustar essa classe para o modelo usando contexto

    fun find(page: Int, size: Int): StudentListResponse {
        val students = studentService.findAll(page, size)
        return StudentMapper.toListResponse(size = size, page = page, students = students)
    }
}
