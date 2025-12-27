package com.ufc.jornal.rest.request.post

data class LikeRequest(
    val like: Boolean,
    val userCode: String,
    val postId: Long,
)
