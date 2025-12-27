package com.ufc.jornal.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.MapsId
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.io.Serializable
import java.time.LocalDateTime

@Entity
@Table(
    name = "post_likes",
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["post_id", "user_id"])
    ]
)
class PostLike(

    @EmbeddedId
    var id: PostLikeId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("postId")
    @JoinColumn(name = "post_id", nullable = false)
    val post: Post,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
)

@Embeddable
data class PostLikeId(

    @Column(name = "post_id")
    val postId: Long,

    @Column(name = "user_id")
    val userId: Long
) : Serializable