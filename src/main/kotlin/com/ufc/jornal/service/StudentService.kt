package com.ufc.jornal.service

import com.ufc.jornal.domain.Student
import com.ufc.jornal.exception.InvalidStudentRequest
import com.ufc.jornal.extencion.isSuperiorLevel
import com.ufc.jornal.mapper.StudentMapper
import com.ufc.jornal.repository.StudentRepository
import com.ufc.jornal.rest.request.student.StudentPutRequest
import com.ufc.jornal.rest.request.student.StudentRequest
import java.time.LocalDateTime
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class StudentService(
    private val studentRepository: StudentRepository,
){

    fun create(request: StudentRequest) =
        StudentMapper.toEntity(request)

    fun save(student: Student) =
        studentRepository.save(student)

    fun findAll(page: Int, size: Int): List<Student> {
        val pageable = PageRequest.of(page, size)
        return studentRepository.findAll(pageable).content
    }

    fun findByCode(code: String): Student =
        studentRepository.findByCode(code)

    fun delete(code: String) {
        studentRepository.deleteByCode(code)
    }

    fun validateRequest(request: StudentRequest) {
        if ( request.isScholarship && !request.isSuperiorLevel() )
            throw InvalidStudentRequest("Request Invalid, para ser bolsista o aluno tem que ser do ensino superior")
    }

    fun update(student: Student, request: StudentPutRequest, updateAt: LocalDateTime): Student {
        request.name?.takeIf { it.isNotBlank() }
            ?.let { student.name = it }

        request.email?.takeIf { it.isNotBlank() }
            ?.let { student.email = it }

        request.password?.takeIf { it.isNotBlank() }
            ?.let { student.password = it }

        request.isScholarship?.takeIf { it != student.isScholarship }
            ?.let { student.isScholarship = it }

        request.educationLevel?.takeIf { it != student.educationLevel }
            ?.let { student.educationLevel = it }

        student.updatedAt = updateAt

        return student
    }

}
