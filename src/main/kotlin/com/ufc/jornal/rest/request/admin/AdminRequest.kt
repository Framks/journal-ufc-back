package com.ufc.jornal.rest.request.admin

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class AdminRequest(
    @field:NotBlank
    val name: String,

    @field:Email
    val email: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val username: String,
)
