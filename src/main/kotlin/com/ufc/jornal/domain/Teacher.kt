package com.ufc.jornal.domain

import com.ufc.jornal.constants.Constant.TEACHER
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.util.UUID

@Entity
@DiscriminatorValue(TEACHER)
class Teacher(

    name: String,

    @Column(unique = true)
    val code: String = "PRO_".plus(UUID.randomUUID().toString()),

    username: String,

    email: String,

    password: String,
) : User(username = username, name = name, email = email, password = password){

}