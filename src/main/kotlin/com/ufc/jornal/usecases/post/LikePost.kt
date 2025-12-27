package com.ufc.jornal.usecases.post

import com.ufc.jornal.domain.PostLike
import com.ufc.jornal.domain.PostLikeId
import com.ufc.jornal.repository.PostLikeRepository
import com.ufc.jornal.rest.request.post.LikeRequest
import com.ufc.jornal.service.PostService
import com.ufc.jornal.service.UserService
import org.springframework.stereotype.Service

@Service
class LikePost(
    private val postService: PostService,
    private val userService: UserService,
    private val postLikeRepository: PostLikeRepository
) {

    fun execute(request: LikeRequest) {

        val post = postService.findById(request.postId)
        val user = userService.findByCode(request.userCode)

        val id = PostLikeId(post.id!!, user.id!!)

        if (postLikeRepository.findByPostIdAndUserId(post.id!!, user.id!!) != null ) return

        postLikeRepository.save(
            PostLike(
                id = id,
                post = post,
                user = user
            )
        )

        post.numberOfLikes = (post.numberOfLikes ?: 0) + 1
        postService.save(post)
    }
}
