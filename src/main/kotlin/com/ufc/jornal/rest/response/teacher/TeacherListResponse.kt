package com.ufc.jornal.rest.response.teacher

data class TeacherListResponse(
    val page: Int,
    val size: Int,
    val teachers: List<TeacherResponse>
)