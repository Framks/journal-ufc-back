package com.ufc.jornal.usecases.student

import com.ufc.jornal.context.student.CreateStudentContext
import com.ufc.jornal.mapper.StudentMapper
import com.ufc.jornal.rest.request.student.StudentRequest
import com.ufc.jornal.service.StudentService
import org.springframework.stereotype.Service

@Service
class StudentCreate(
    private val studentService: StudentService,
){

    fun create(request: StudentRequest)
            = CreateStudentContext(request = request)
        .let { validateRequest(it) }
        .let { createStudent(it) }
        .let { encriptPassword(it) }
        .let { save(it) }
        .let { StudentMapper.toResponse(it.student!!) }

    private fun validateRequest(context: CreateStudentContext) =
        studentService.validateRequest(context.request)
            .let{ context }

    private fun createStudent(context: CreateStudentContext) =
        context
            .let { studentService.create(it.request) }
            .let { context.addStudent(it) }

    private fun save(context: CreateStudentContext) =
        context
            .let { studentService.save(it.student!!) }
            .let { context }

    private fun encriptPassword(context: CreateStudentContext) =
        context
            .let { studentService.encriptPassword(it.student!!) }
            .let { context.addStudent(it) }
}
