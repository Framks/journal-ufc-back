package com.ufc.jornal.domain;

import com.ufc.jornal.constants.Constant.STUDENT
import com.ufc.jornal.domain.enums.EducationLevel
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.util.UUID

@Entity
@DiscriminatorValue(STUDENT)
class Student(

    name: String,

    username: String,

    @Column(unique = true)
    val code: String = "ALU_".plus(UUID.randomUUID().toString()),

    email: String,

    password: String,

    @Enumerated(EnumType.STRING)
    var educationLevel: EducationLevel,

    @Column(name = "is_scholarship")
    var isScholarship: Boolean,
): User(username = username, name = name, email = email, password = password )