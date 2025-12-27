package com.ufc.jornal.rest.response.post

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class CommentResponse(
    val id: String,
    val content: String,

    @JsonProperty("author_name")
    val authorName: String,

    @JsonProperty("post_id")
    val postId: String,

    @JsonProperty("created_at")
    val createdAt: LocalDateTime,
)
