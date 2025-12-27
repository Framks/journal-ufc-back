package com.ufc.jornal.rest.request.post

import com.fasterxml.jackson.annotation.JsonProperty

data class UpdatePostRequest(
    val content: String,
    val tags: List<String>?,

    @param:JsonProperty("author_code")
    val authorCode: String,
)
