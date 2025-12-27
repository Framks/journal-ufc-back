package com.ufc.jornal.repository

import com.ufc.jornal.domain.PostLike
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostLikeRepository: JpaRepository<PostLike, Long> {

    fun findByPostIdAndUserId(postId: Long, userId: Long): PostLike?
}