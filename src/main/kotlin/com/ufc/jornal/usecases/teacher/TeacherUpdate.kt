package com.ufc.jornal.usecases.teacher

import com.ufc.jornal.context.teacher.ContextTeacherUpdate
import com.ufc.jornal.mapper.TeacherMapper
import com.ufc.jornal.rest.request.teacher.TeacherPutRequest
import com.ufc.jornal.service.TeacherService
import org.springframework.stereotype.Service

@Service
class TeacherUpdate(
    private val teacherService: TeacherService,
) {

    fun update(code: String, request: TeacherPutRequest) =
        ContextTeacherUpdate(code, request)
            .let { fechTeacher(it) }
            .let { updateInfo(it) }
            .let { save(it) }
            .let { TeacherMapper.toResponse(it.teacher) }

    private fun fechTeacher(context: ContextTeacherUpdate) = 
        context
            .let { teacherService.findByCode(it.code) }
            .let { context.addTeacher(it!!) }

    private fun updateInfo(context: ContextTeacherUpdate) =
        teacherService.update(
            teacher = context.teacher!!,
            request = context.request,
            updateAt = context.updateAt
        ).let { context.addTeacher(it) }

    private fun save(context: ContextTeacherUpdate) =
        context
            .let { teacherService.save(it.teacher!!) }
            .let { context.addTeacher(it) }
}