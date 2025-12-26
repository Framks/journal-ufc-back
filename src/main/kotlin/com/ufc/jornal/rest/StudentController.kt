package com.ufc.jornal.rest

import com.ufc.jornal.constants.URL.STUDENT
import com.ufc.jornal.rest.request.student.StudentPutRequest
import com.ufc.jornal.rest.request.student.StudentRequest
import com.ufc.jornal.rest.response.student.StudentListResponse
import com.ufc.jornal.rest.response.student.StudentResponse
import com.ufc.jornal.usecases.student.StudentCreate
import com.ufc.jornal.usecases.student.StudentDelete
import com.ufc.jornal.usecases.student.StudentFindByCode
import com.ufc.jornal.usecases.student.StudentList
import com.ufc.jornal.usecases.student.StudentUpdate
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(STUDENT)
class StudentController(
    private val studentCreate: StudentCreate,
    private val studentList: StudentList,
    private val studentFindByCode: StudentFindByCode,
    private val studentUpdate: StudentUpdate,
    private val studentDelete: StudentDelete,
){

    @PostMapping
    fun create(
        @Valid
        @RequestBody
        request: StudentRequest
    ): StudentResponse {
        return studentCreate.create(request)
    }

    @GetMapping
    fun findAll(
        @RequestParam(defaultValue = "0")
        page: Int,

        @RequestParam(defaultValue = "10")
        size: Int
    ): StudentListResponse {
        return studentList.find(page, size)
    }

    @GetMapping("/{code}")
    fun findByCode(
        @PathVariable
        code: String
    ): StudentResponse =
        studentFindByCode.find(code)

    @PutMapping("/{code}")
    fun update(
        @PathVariable
        code: String,

        @Valid
        @RequestBody
        request: StudentPutRequest
    ): StudentResponse =
        studentUpdate.update(code, request)

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @PathVariable
        code: String
    ) {
        studentDelete.delete(code)
    }
}
