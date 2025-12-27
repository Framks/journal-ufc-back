package com.ufc.jornal.rest.request.post

import com.fasterxml.jackson.annotation.JsonProperty

data class PostPosResquest(

    val content: String,

    @param:JsonProperty(value = "author_code")
    val authorCode: String,

    val media: List<String>?,

    @param:JsonProperty(value = "is_featured")
    val isFeatured: Boolean?,

    val tags: List<String>?,
)
