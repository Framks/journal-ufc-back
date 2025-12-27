package com.ufc.jornal.usecases.post

import com.ufc.jornal.service.PostService
import org.springframework.stereotype.Service

@Service
class DeletePost(
    private val postService: PostService
) {

    fun execute(postId: Long) {
        val post = postService.findById(postId)
        post.deleted = true
        postService.save(post)
    }
}
