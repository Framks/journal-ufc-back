package com.ufc.jornal.usecases.student

import com.ufc.jornal.service.StudentService
import org.springframework.stereotype.Service

@Service
class StudentDelete(
    private val studentService: StudentService,
){

    fun delete(code: String) =
        studentService.delete(code)
}
