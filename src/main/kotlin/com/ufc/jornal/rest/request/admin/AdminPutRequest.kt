package com.ufc.jornal.rest.request.admin

data class AdminPutRequest(
    val name: String?,
    val password: String?,
    val username: String?,
    val email: String?,
)