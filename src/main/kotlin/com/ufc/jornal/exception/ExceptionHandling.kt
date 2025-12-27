package com.ufc.jornal.exception

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandling {

    val logger = LoggerFactory.getLogger(ExceptionHandling::class.java)

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<String> {
        logger.error(exception.message, exception)

        return ResponseEntity.internalServerError().body("deu problema \nproblema deu: ${exception.message}")
    }

    @ExceptionHandler(InvalidStudentRequest::class)
    fun handlePostStudent(request: InvalidStudentRequest, exception: Exception): ResponseEntity<String> {
        logger.warn(exception.message, exception)
        return ResponseEntity.badRequest().body(exception.message)
    }

    @ExceptionHandler(UnauthorizedException::class)
    fun handleUnauthorizedException(request: UnauthorizedException, exception: Exception): ResponseEntity<String> {
        logger.warn(exception.message, exception)
        return ResponseEntity.status(400).body(exception.message)
    }
}