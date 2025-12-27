package com.ufc.jornal.rest

import com.ufc.jornal.dto.TokenDto
import com.ufc.jornal.rest.request.LoginRequest
import com.ufc.jornal.usecases.UserAuthentication
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val loginUseCase: UserAuthentication
) {

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    fun login(
        @RequestBody
        request: LoginRequest

    ): TokenDto {

        return loginUseCase.login(
            request.username,
            request.password
        )
    }
}