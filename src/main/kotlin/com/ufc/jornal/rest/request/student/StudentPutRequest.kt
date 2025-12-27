package com.ufc.jornal.rest.request.student

import com.fasterxml.jackson.annotation.JsonProperty
import com.ufc.jornal.domain.enums.EducationLevel
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class StudentPutRequest(
    @field:NotBlank
    val name: String?,

    @field:Email
    val email: String?,

    @field:NotBlank
    val password: String?,

    @field:NotBlank
    val username: String?,

    @param:JsonProperty("education_level")
    val educationLevel: EducationLevel?,

    @param:JsonProperty("is_scholarship")
    val isScholarship: Boolean?,
)