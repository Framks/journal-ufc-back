package com.ufc.jornal.rest.response.student

import com.fasterxml.jackson.annotation.JsonProperty
import com.ufc.jornal.domain.enums.EducationLevel

data class StudentResponse(

    val name: String,

    val email: String,

    val code: String,

    val username: String,

    @JsonProperty("education_level")
    val educationLevel: EducationLevel,

    @JsonProperty("is_scholarship")
    val isScholarship: Boolean,
)
