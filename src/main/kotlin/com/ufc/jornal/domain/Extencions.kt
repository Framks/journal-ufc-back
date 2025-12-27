package com.ufc.jornal.domain

import com.ufc.jornal.domain.enums.UserRole

fun User.role(): UserRole =
    when (this) {
        is Admin -> UserRole.ADMIN
        is Teacher -> UserRole.TEACHER
        is Student -> UserRole.STUDENT
        else -> error("Unknown user role")
    }