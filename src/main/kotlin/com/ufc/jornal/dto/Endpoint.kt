package com.ufc.jornal.dto

import org.springframework.http.HttpMethod


data class Endpoint(
    val method: HttpMethod,
    val path: String
)
