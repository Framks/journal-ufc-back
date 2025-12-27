package com.ufc.jornal.usecases.teacher

import com.ufc.jornal.context.teacher.ContextTeacherFindList
import com.ufc.jornal.mapper.TeacherMapper
import com.ufc.jornal.service.TeacherService
import org.springframework.stereotype.Service

@Service
class TeacherList(
    private val teacherService: TeacherService,
){

    fun find(size: Int, page: Int) =
        ContextTeacherFindList(page = page, size = size)
            .let { fechList(it) }
            .let { TeacherMapper.toTeacherList(it.list!!, it.page, it.size) }

    private fun fechList(context: ContextTeacherFindList) =
        context
            .let { teacherService.findAll(it.page, it.size) }
            .let { context.addList(it) }
}