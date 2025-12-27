package com.ufc.jornal.mapper

import com.ufc.jornal.domain.Comment
import com.ufc.jornal.rest.response.post.CommentResponse

object CommentMapper {

    fun toResponse(comment: Comment): CommentResponse =
        CommentResponse(
            id = comment.id.toString(),
            content = comment.content,
            authorName = comment.author.name,
            postId = comment.post.id.toString(),
            createdAt = comment.createdAt
        )
}