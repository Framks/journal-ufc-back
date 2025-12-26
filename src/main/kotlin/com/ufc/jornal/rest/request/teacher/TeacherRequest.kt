package com.ufc.jornal.rest.request.teacher

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class TeacherRequest(
    @NotBlank
    val name: String,

    @Email
    val email: String,

    @NotBlank
    val password: String,

    @NotBlank
    val username: String,
)