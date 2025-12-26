package com.ufc.jornal.rest.response.student

data class StudentListResponse(
    val page: Int,
    val size: Int,
    val students: List<StudentResponse>
)
