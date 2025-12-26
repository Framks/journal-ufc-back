package com.ufc.jornal.usecases.student

import com.ufc.jornal.context.student.ContextPutStudent
import com.ufc.jornal.mapper.StudentMapper
import com.ufc.jornal.rest.request.student.StudentPutRequest
import com.ufc.jornal.service.StudentService
import org.springframework.stereotype.Service

@Service
class StudentUpdate(
    private val studentService: StudentService,
){

    fun update(code: String, request: StudentPutRequest) =
        ContextPutStudent(code, request)
            .let { findStudent(it) }
            .let { updateInfos(it) }
            .let { save(it) }
            .let { StudentMapper.toResponse(it.student!!) }


    private fun findStudent(context: ContextPutStudent) =
        studentService.findByCode(context.code)
            .let { context.addSudent(it) }
    
    private fun updateInfos(context: ContextPutStudent) =
        studentService.update(
            student = context.student!!,
            request = context.request,
            updateAt = context.updatedAt
        ) .let { context.addSudent(it) }

    private fun save(context: ContextPutStudent) =
        studentService.save(context.student!!)
            .let { context }

}
