package com.ufc.jornal.rest.request.teacher

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class TeacherRequest(
    @field:NotBlank
    val name: String,

    @field:Email
    val email: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val username: String,
)