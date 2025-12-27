package com.ufc.jornal.rest;

import com.ufc.jornal.constants.URL.TEACHER
import com.ufc.jornal.rest.request.teacher.TeacherPutRequest
import com.ufc.jornal.rest.request.teacher.TeacherRequest
import com.ufc.jornal.rest.response.teacher.TeacherListResponse
import com.ufc.jornal.rest.response.teacher.TeacherResponse
import com.ufc.jornal.usecases.teacher.TeacherCreate
import com.ufc.jornal.usecases.teacher.TeacherDelete
import com.ufc.jornal.usecases.teacher.TeacherFindByCode
import com.ufc.jornal.usecases.teacher.TeacherList
import com.ufc.jornal.usecases.teacher.TeacherUpdate
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
@RequestMapping(TEACHER)
class TeacherController(
    private val teacherCreate: TeacherCreate,
    private val teacherList: TeacherList,
    private val teacherFindByCode: TeacherFindByCode,
    private val teacherUpdate: TeacherUpdate,
    private val teacherDelete: TeacherDelete,
){

    @PostMapping()
    fun create(
        @Valid
        @RequestBody
        request: TeacherRequest

    ): TeacherResponse {
        return teacherCreate.create(request)
    }

    @GetMapping
    fun findAll(
        @RequestParam(defaultValue = "0")
        page: Int,

        @RequestParam(defaultValue = "10")
        size: Int
    ): TeacherListResponse {
        return teacherList.find(page, size)
    }

    @GetMapping("/{code}")
    fun findById(
        @PathVariable
        code: String

    ): TeacherResponse =
        teacherFindByCode.find(code)

    @PutMapping("/{code}")
    fun update(
        @PathVariable
        code: String,

        @Valid
        @RequestBody
        request: TeacherPutRequest
    ): TeacherResponse =
        teacherUpdate.update(code, request)

    @DeleteMapping("/{code}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(
        @PathVariable code: String
    ) {
        teacherDelete.delete(code)
    }
}
