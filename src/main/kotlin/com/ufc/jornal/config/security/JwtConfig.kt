package com.ufc.jornal.config.security

import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JwtConfig(
    @param:Value("\${jwt.private.key}")
    private val privateKey: String,

    @param:Value("\${jwt.public.key}")
    private val publicKey: String
) {

    @Bean
    fun rsaPrivateKey(): RSAPrivateKey =
        KeyFactory.getInstance("RSA")
            .generatePrivate(PKCS8EncodedKeySpec(decode(privateKey))) as RSAPrivateKey

    @Bean
    fun rsaPublicKey(): RSAPublicKey =
        KeyFactory.getInstance("RSA")
            .generatePublic(X509EncodedKeySpec(decode(publicKey))) as RSAPublicKey

    private fun decode(key: String): ByteArray =
        Base64.getDecoder().decode(
            key.replace(Regex("-----.*?-----"), "")
                .replace("\\n", "")
                .trim()
        )
}