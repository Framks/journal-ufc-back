package com.ufc.jornal.usecases.post

import com.ufc.jornal.exception.UnauthorizedException
import com.ufc.jornal.mapper.PostMapper
import com.ufc.jornal.rest.request.post.UpdatePostRequest
import com.ufc.jornal.rest.response.post.PostResponse
import com.ufc.jornal.service.PostService
import com.ufc.jornal.usecases.FindOrCreateTags
import java.time.LocalDateTime
import org.springframework.stereotype.Service

@Service
class UpdatePost(
    private val postService: PostService,
    private val findOrCreateTags: FindOrCreateTags
) {

    fun execute(postId: Long, request: UpdatePostRequest): PostResponse {

        val post = postService.findById(postId)

        if (post.author.code != request.authorCode) {
            throw UnauthorizedException()
        }

        post.content = request.content
        post.updatedAt = LocalDateTime.now()

        request.tags?.let {
            post.tags.clear()
            post.tags.addAll(findOrCreateTags.findOrCreate(it))
        }

        postService.save(post)

        return PostMapper.toResponse(postService.save(post))
    }
}
