package com.ufc.jornal.usecases.post

import com.ufc.jornal.mapper.PostMapper
import com.ufc.jornal.rest.response.post.PostResponse
import com.ufc.jornal.service.PostService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
@Service
class ListPost(
    private val postService: PostService,

) {

    fun list(
        authorCode: String?,
        query: String?,
        tags: List<String>?,
        pageable: Pageable
    ): Page<PostResponse> {
        return postService.listPosts(authorCode, query, tags, pageable)
            .map { PostMapper.toResponse(it) }
    }
}