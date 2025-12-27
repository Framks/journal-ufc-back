package com.ufc.jornal.mapper

import com.ufc.jornal.domain.Post
import com.ufc.jornal.rest.response.post.PostResponse
import com.ufc.jornal.rest.response.post.UserResponse

object PostMapper {
    fun toResponse(post: Post) =
        PostResponse(
            author = UserResponse(
                name = post.author.name,
                code = post.author.code
            ),
            content = post.content,
            media = post.media,
            isFeatured = post.isFeatured,
            tags = post.tags.map { it.name },
            numberOfComments = post.numberOfComments!!,
            numberOfLikes = post.numberOfLikes!!,
            createdAt = post.createdAt
        )
}