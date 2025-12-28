package com.ufc.jornal.usecases.post

import com.ufc.jornal.mapper.PostMapper
import com.ufc.jornal.service.PostService
import org.springframework.stereotype.Service

@Service
class GetPostById(
    private val postService: PostService,
) {

    fun find(id: Long) =
        PostMapper.toResponse(postService.findById(id))
}