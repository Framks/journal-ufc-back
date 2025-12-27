package com.ufc.jornal.service

import com.ufc.jornal.domain.Admin
import com.ufc.jornal.domain.Student
import com.ufc.jornal.domain.Teacher
import com.ufc.jornal.domain.User
import com.ufc.jornal.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun findByCode(code: String) =
        userRepository.findByCode(code)

    fun canCreatePost(user: User) =
        when (user) {
            is Teacher -> true
            is Admin -> true
            is Student -> user.isScholarship
            else -> false
        }
}