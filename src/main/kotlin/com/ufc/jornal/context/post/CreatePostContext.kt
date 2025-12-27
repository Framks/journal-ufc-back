package com.ufc.jornal.context.post

import com.ufc.jornal.domain.Post
import com.ufc.jornal.domain.Tag
import com.ufc.jornal.domain.User
import com.ufc.jornal.rest.request.post.PostPosResquest

data class CreatePostContext(
    val request: PostPosResquest,
    val author: User? = null,
    val approved: Boolean? = null,
    val tags: Set<Tag>? = null,
    val post: Post? = null
){
    fun addAuthor(author: User) = copy(author = author)
    fun addApproved(approved: Boolean) = copy(approved = approved)
    fun addTags(tags: Set<Tag>) = copy(tags = tags)
    fun addPost(post: Post) = copy(post = post)
}
