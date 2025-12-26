package com.ufc.jornal.rest.request.student

import com.fasterxml.jackson.annotation.JsonProperty
import com.ufc.jornal.domain.enums.EducationLevel
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class StudentPutRequest(
    @NotBlank
    val name: String?,

    @Email
    val email: String?,

    @NotBlank
    val password: String?,

    @NotBlank
    val username: String?,

    @JsonProperty("education_level")
    val educationLevel: EducationLevel?,

    @JsonProperty("is_scholarship")
    val isScholarship: Boolean?,
)