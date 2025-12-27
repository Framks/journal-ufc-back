package com.ufc.jornal.rest

import com.ufc.jornal.constants.URL.POST
import com.ufc.jornal.rest.request.post.CreateCommentRequest
import com.ufc.jornal.rest.request.post.LikeRequest
import com.ufc.jornal.rest.request.post.PostPosResquest
import com.ufc.jornal.rest.request.post.UpdatePostRequest
import com.ufc.jornal.rest.response.post.CommentResponse
import com.ufc.jornal.rest.response.post.PostResponse
import com.ufc.jornal.usecases.post.CreateComment
import com.ufc.jornal.usecases.post.CreatePost
import com.ufc.jornal.usecases.post.DeletePost
import com.ufc.jornal.usecases.post.LikePost
import com.ufc.jornal.usecases.post.ListPost
import com.ufc.jornal.usecases.post.UpdatePost
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(POST)
class PostContoller(
    private val createPost: CreatePost,
    private val listPost: ListPost,
    private val updatePost: UpdatePost,
    private val deletePost: DeletePost,
    private val createComment: CreateComment,
    private val likePost: LikePost,
    ) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPost(
        @RequestBody
        request: PostPosResquest
    ): PostResponse {
        return createPost.post(request)
    }

    @GetMapping
    fun listPosts(
        @RequestParam(required = false)
        authorCode: String?,

        @RequestParam(required = false)
        q: String?,

        @RequestParam(required = false)
        tags: List<String>?,

        @PageableDefault(
            size = 10,
            sort = ["createdAt"],
            direction = Sort.Direction.DESC
        ) pageable: Pageable
    ): Page<PostResponse> {
        return listPost.list(authorCode, q, tags, pageable)
    }

    @PutMapping("/{id}")
    fun updatePost(
        @PathVariable
        id: Long,

        @RequestBody
        request: UpdatePostRequest
    ): PostResponse {
        return updatePost.execute(id, request)
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletePost(
        @PathVariable
        id: Long
    ) {
        deletePost.execute(id)
    }

    @PostMapping("/{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    fun comment(
        @PathVariable
        postId: Long,

        @RequestBody
        request: CreateCommentRequest
    ): CommentResponse {
        return createComment.execute(postId, request)
    }

    @PostMapping("/like")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun like(
        @RequestBody
        request: LikeRequest,
        ) {
        likePost.execute(request)
    }

}