package com.ufc.jornal.rest.request.teacher

data class TeacherPutRequest(
    val name: String?,
    val password: String?,
    val username: String?,
    val email: String?,
)