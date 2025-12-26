package com.ufc.jornal.service

import com.ufc.jornal.domain.Teacher
import com.ufc.jornal.mapper.TeacherMapper
import com.ufc.jornal.repository.TeacherRepository
import com.ufc.jornal.rest.request.teacher.TeacherPutRequest
import com.ufc.jornal.rest.request.teacher.TeacherRequest
import java.time.LocalDateTime
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class TeacherService(
    private val teacherRepository: TeacherRepository,
){

    fun create(teacher: TeacherRequest) =
        TeacherMapper.toEntity(teacher)

    fun save(teacher: Teacher) =
        teacherRepository.save(teacher)

    fun findAll(page: Int, size: Int): List<Teacher> {
        val pageable = PageRequest.of(page, size)
        return teacherRepository.findAll(pageable).content
    }
    
    fun findByCode(code: String) =
        teacherRepository.findByCode(code)

    fun delete(code: String) =
        teacherRepository.deleteByCode(code)

    fun update(teacher: Teacher, request: TeacherPutRequest, updateAt: LocalDateTime): Teacher {

        request.name?.takeIf { it.isNotBlank() }
            ?.let { teacher.name = it }

        request.email?.takeIf { it.isNotBlank() }
            ?.let { teacher.email = it }

        request.password?.takeIf { it.isNotBlank() }
            ?.let { teacher.password = it }

        teacher.updatedAt = updateAt

        return teacher
    }
}