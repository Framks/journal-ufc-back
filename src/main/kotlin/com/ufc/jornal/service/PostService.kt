package com.ufc.jornal.service

import com.ufc.jornal.domain.Post
import com.ufc.jornal.domain.Tag
import com.ufc.jornal.domain.User
import com.ufc.jornal.repository.PostRepository
import com.ufc.jornal.rest.request.post.PostPosResquest
import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository,
) {
    fun createPost(author: User, tags: Set<Tag>, request: PostPosResquest, approved: Boolean): Post  =
        Post(
            author = author,
            content = request.content,
            media = request.media,
            approved = approved,
            tags = tags.toMutableSet(),
            isFeatured = request.isFeatured?:false,
            numberOfComments = 0L,
            numberOfLikes = 0L,
            comments = null,
            likes = null,
        )

    fun save(post: Post) =
        postRepository.save(post)

    fun listPosts(
        authorCode: String?,
        query: String?,
        tags: List<String>?,
        pageable: org.springframework.data.domain.Pageable
    ) =
        postRepository.search(
            authorCode = authorCode,
            query = query,
            tags = tags,
            pageable = pageable
        )

    fun findById(postId: Long): Post =
        postRepository.findById(postId).orElseThrow { Exception("Post not found") }
}