package com.ufc.jornal.usecases

import com.ufc.jornal.service.JwtService
import com.ufc.jornal.dto.TokenDto
import com.ufc.jornal.exception.UnauthorizedException
import com.ufc.jornal.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserAuthentication(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenService: JwtService
) {
    fun login(username: String, password: String): TokenDto {
        val user = userRepository.findByUsername(username)
            ?: throw UnauthorizedException()

        if (!passwordEncoder.matches(password, user.password))
            throw UnauthorizedException()

        return tokenService.generateToken(user)
    }
}