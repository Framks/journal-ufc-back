package com.ufc.jornal.rest.response.admin

data class AdminListResponse(
    val page: Int,
    val size: Int,
    val teachers: List<AdminResponse>
)
