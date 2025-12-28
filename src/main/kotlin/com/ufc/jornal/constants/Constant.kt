package com.ufc.jornal.constants

import com.ufc.jornal.dto.Endpoint
import org.springframework.http.HttpMethod

object Constant {
    const val TEACHER = "TEACHER"
    const val STUDENT = "STUDENT"
    const val ADMIN = "ADMIN"
}

object URL {
    const val STUDENT = "/students"
    const val TEACHER = "/teachers"
    const val ADMIN = "/admins"
    const val POST = "/posts"
}

object Endpoints {

    val PUBLIC = listOf(
        Endpoint(HttpMethod.POST, "/auth/login"),
        Endpoint(HttpMethod.GET, "/posts/*"),
        Endpoint(HttpMethod.POST, "/students")
    )

    val ADMIN = listOf(
        Endpoint(HttpMethod.POST, "/admins"),
        Endpoint(HttpMethod.POST, "/teachers"),
        Endpoint(HttpMethod.PUT, "/teachers/*"),
        Endpoint(HttpMethod.DELETE, "/teachers/*")
    )

    val AUTHENTICATED = listOf(
        Endpoint(HttpMethod.POST, "/posts/*/comments"),
        Endpoint(HttpMethod.POST, "/posts/like")
    )
}
