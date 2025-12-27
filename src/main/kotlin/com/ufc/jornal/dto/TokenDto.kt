package com.ufc.jornal.dto

data class TokenDto(
    val accessToken: String,
    val tokenType: String = "Bearer",
    val expiresIn: Long
)

