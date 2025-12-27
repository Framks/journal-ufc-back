package com.ufc.jornal.service

import com.ufc.jornal.domain.User
import com.ufc.jornal.domain.role
import com.ufc.jornal.dto.TokenDto
import java.time.Instant
import java.time.temporal.ChronoUnit
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service

@Service
class JwtService(
    private val jwtEncoder: JwtEncoder,

    @Value("\${jwt.access-token-expiration-minutes}")
    private val expiration: Long,

    @Value("\${jwt.issuer}")
    private val issuer: String
) {
    fun generateToken(user: User): TokenDto {
        val now = Instant.now()

        val claims = JwtClaimsSet.builder()
            .issuer(issuer)
            .issuedAt(now)
            .expiresAt(now.plus(expiration, ChronoUnit.MINUTES))
            .subject(user.id.toString())
            .claim("email", user.email)
            .claim("roles", user.role().name)
            .build()
        val token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).tokenValue
        return TokenDto(accessToken = token, expiresIn = expiration)
    }
}