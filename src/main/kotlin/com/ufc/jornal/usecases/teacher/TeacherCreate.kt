package com.ufc.jornal.usecases.teacher

import com.ufc.jornal.context.teacher.CreateTeacherContext
import com.ufc.jornal.mapper.TeacherMapper
import com.ufc.jornal.rest.request.teacher.TeacherRequest
import com.ufc.jornal.service.TeacherService
import org.springframework.stereotype.Service

@Service
class TeacherCreate(
    private val teacherService: TeacherService,
){

    fun create(request: TeacherRequest)
    = CreateTeacherContext(request)
        .let { createTeacher(it) }
        .let { save(it) }
        .let { TeacherMapper.toResponse(it.teacher) }

    private fun createTeacher(context: CreateTeacherContext) =
        context
            .let { teacherService.create(it.request) }
            .let{ context.addTeacher(it) }

    private fun save(context: CreateTeacherContext) =
        context
            .let { teacherService.save(it.teacher!!) }
            .let { context }
}