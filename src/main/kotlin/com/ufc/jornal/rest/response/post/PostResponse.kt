package com.ufc.jornal.rest.response.post

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class PostResponse(
    val content: String,
    val author: UserResponse,
    val media: List<String>?,
    @JsonProperty("is_featured")
    val isFeatured: Boolean,
    val tags: List<String>?,
    val numberOfComments: Long,
    val numberOfLikes: Long,
    @JsonProperty("created_at")
    val createdAt: LocalDateTime,
)

data class UserResponse(
    val name: String,
    val code: String,
)