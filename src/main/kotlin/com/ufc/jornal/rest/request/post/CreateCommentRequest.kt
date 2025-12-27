package com.ufc.jornal.rest.request.post

import com.fasterxml.jackson.annotation.JsonProperty

data class CreateCommentRequest(
    @param:JsonProperty("author_code")
    val authorCode: String,
    val content: String,
)
