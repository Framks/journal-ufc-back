package com.ufc.jornal.usecases.post

import com.ufc.jornal.domain.Comment
import com.ufc.jornal.mapper.CommentMapper
import com.ufc.jornal.repository.CommentRepository
import com.ufc.jornal.rest.request.post.CreateCommentRequest
import com.ufc.jornal.rest.response.post.CommentResponse
import com.ufc.jornal.service.PostService
import com.ufc.jornal.service.UserService
import org.springframework.stereotype.Service

@Service
class CreateComment(
    private val postService: PostService,
    private val userService: UserService,
    private val commentRepository: CommentRepository
) {

    fun execute(postId: Long, request: CreateCommentRequest): CommentResponse {

        val post = postService.findById(postId)
        val author = userService.findByCode(request.authorCode)

        val comment = Comment(
            author = author,
            content = request.content,
            post = post
        )

        post.numberOfComments = (post.numberOfComments ?: 0) + 1

        return CommentMapper.toResponse(commentRepository.save(comment))
    }
}
