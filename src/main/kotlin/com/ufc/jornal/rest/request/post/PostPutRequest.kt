package com.ufc.jornal.rest.request.post

import com.fasterxml.jackson.annotation.JsonProperty

data class PostPutRequest(

    val content: String?,

    val media: List<String>?,

    @JsonProperty(value = "is_featured")
    val isFeatured: Boolean?,
)
