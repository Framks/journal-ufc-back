package com.ufc.jornal.usecases.post

import com.ufc.jornal.context.post.CreatePostContext
import com.ufc.jornal.domain.Student
import com.ufc.jornal.exception.PermissionDeniedForPostCreationException
import com.ufc.jornal.mapper.PostMapper
import com.ufc.jornal.rest.request.post.PostPosResquest
import com.ufc.jornal.service.PostService
import com.ufc.jornal.service.UserService
import com.ufc.jornal.usecases.FindOrCreateTags
import org.springframework.stereotype.Service

@Service
class CreatePost(
    private val userService: UserService,
    private val postService: PostService,
    private val findOrCreateTags: FindOrCreateTags
){

    fun post(request: PostPosResquest) =
        CreatePostContext(request = request)
            .let { findAuthor(it) }
            .let { hasPermission(it) }
            .let { needValidate(it) }
            .let { resolveTags(it) }
            .let { createPost(it) }
            .let { savePost(it) }
            .let { PostMapper.toResponse(it.post!!) }

    private fun findAuthor(context: CreatePostContext) =
        userService.findByCode(context.request.authorCode)
            .let { context.addAuthor(it) }

    private fun hasPermission(context: CreatePostContext) =
        takeIf { userService.canCreatePost(context.author!!) }
            ?.let { context }
            ?: throw PermissionDeniedForPostCreationException()

    private fun needValidate(context: CreatePostContext) =
        takeIf { context.author is Student }
            ?.let { context.addApproved(false) }
            ?: context.addApproved(true)

    private fun resolveTags(context: CreatePostContext) =
        context.request.tags
            ?.let { findOrCreateTags.findOrCreate(it) }
            ?.let { context.addTags(it) }
            ?: context

    private fun createPost(context: CreatePostContext) =
        postService.createPost(
            author = context.author!!,
            request = context.request,
            approved = context.approved!!,
            tags = context.tags ?: emptySet()
        )
            .let { context.addPost(it) }

    private fun savePost(context: CreatePostContext) =
        postService.save(context.post!!)
            .let { context.addPost(it) }
}