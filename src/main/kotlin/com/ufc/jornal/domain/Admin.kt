package com.ufc.jornal.domain

import com.ufc.jornal.constants.Constant.ADMIN
import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.util.UUID

@Entity
@DiscriminatorValue(ADMIN)
class Admin(

    name: String,

    username: String,

    @Column(unique = true)
    val code: String = "ADM_".plus(UUID.randomUUID().toString()),

    email: String,

    password: String,
): User(username = username, name = name, email = email, password = password)