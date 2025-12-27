package com.ufc.jornal.mapper

import com.ufc.jornal.domain.Teacher
import com.ufc.jornal.rest.request.teacher.TeacherRequest
import com.ufc.jornal.rest.response.teacher.TeacherListResponse
import com.ufc.jornal.rest.response.teacher.TeacherResponse

object TeacherMapper {

    fun toResponse(teacher: Teacher?)
    = TeacherResponse(
        name = teacher?.name!!,
        email = teacher.email,
        username = teacher.username,
        code = teacher.code,
    )

    fun toEntity(
        teacher: TeacherRequest,
    ) = Teacher(
        name = teacher.name,
        email = teacher.email,
        password = teacher.password,
        username = teacher.username,
    )

    fun toTeacherList(list: List<Teacher>, page: Int, size: Int)
    = TeacherListResponse(
        page = page,
        size = size,
        teachers = list.map { toResponse(it) }.toList()
    )
}