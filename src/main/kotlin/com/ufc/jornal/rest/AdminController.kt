package com.ufc.jornal.rest

import com.ufc.jornal.constants.URL.ADMIN
import com.ufc.jornal.rest.request.admin.AdminPutRequest
import com.ufc.jornal.rest.request.admin.AdminRequest
import com.ufc.jornal.rest.response.admin.AdminListResponse
import com.ufc.jornal.rest.response.admin.AdminResponse
import com.ufc.jornal.usecases.admin.AdminCreate
import com.ufc.jornal.usecases.admin.AdminDelete
import com.ufc.jornal.usecases.admin.AdminFindByCode
import com.ufc.jornal.usecases.admin.AdminList
import com.ufc.jornal.usecases.admin.AdminUpdate
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(ADMIN)
class AdminController(
    private val adminFindByCode: AdminFindByCode,
    private val adminCreate: AdminCreate,
    private val adminDelete: AdminDelete,
    private val adminList: AdminList,
    private val adminUpdate: AdminUpdate
    ) {

    @GetMapping("/{code}")
    fun findByCode(
        @PathVariable
        code: String,
    ): AdminResponse {
        return adminFindByCode.find(code)
    }

    @GetMapping
    fun list(
        @RequestParam(required = false, defaultValue = "0")
        page: Int,

        @RequestParam(required = false, defaultValue = "10")
        size: Int,
    ): AdminListResponse {
        return adminList.find(page = page, size =  size)
    }

    @PostMapping
    fun create(
        @RequestBody
        request: AdminRequest
    ): AdminResponse {
        return adminCreate.create(request)
    }

    @PutMapping("/{code}")
    fun update(
        @RequestBody
        request: AdminPutRequest,

        @PathVariable
        code: String
    ): AdminResponse {
        return adminUpdate.update(code = code, request = request)
    }

    @DeleteMapping("/{code}")
    fun delete(
        @PathVariable
        code: String
    ) {
        adminDelete.delete(code)
    }
}