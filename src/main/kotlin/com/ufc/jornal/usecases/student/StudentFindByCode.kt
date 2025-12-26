package com.ufc.jornal.usecases.student

import com.ufc.jornal.mapper.StudentMapper
import com.ufc.jornal.service.StudentService
import org.springframework.stereotype.Service

@Service
class StudentFindByCode(
    private val studentService: StudentService,
){

    fun find(code: String) =
        studentService.findByCode(code)
            .let { StudentMapper.toResponse(it) }

}
