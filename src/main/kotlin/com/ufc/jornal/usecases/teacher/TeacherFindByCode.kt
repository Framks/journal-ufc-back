package com.ufc.jornal.usecases.teacher

import com.ufc.jornal.context.teacher.ContextTeacherFindByCode
import com.ufc.jornal.mapper.TeacherMapper
import com.ufc.jornal.service.TeacherService
import org.springframework.stereotype.Service

@Service
class TeacherFindByCode(
    private val teacherService: TeacherService,
) {

    fun find(code: String) =
        ContextTeacherFindByCode(code)
            .let { fechTeacher(it) }
            .let { TeacherMapper.toResponse(it.teacher) }

    private fun fechTeacher(context: ContextTeacherFindByCode) =
        context
            .let { teacherService.findByCode(it.code) }
            .let { context.addTeacher(it!!) }
}