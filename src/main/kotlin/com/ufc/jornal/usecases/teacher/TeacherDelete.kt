package com.ufc.jornal.usecases.teacher

import com.ufc.jornal.service.TeacherService
import org.springframework.stereotype.Service

@Service
class TeacherDelete(
    private val teacherService: TeacherService,
) {
    fun delete(code: String){
        teacherService.delete(code)
    }
}