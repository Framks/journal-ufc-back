package com.ufc.jornal.domain;

import jakarta.persistence.CascadeType
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "posts")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    val author: User,

    @Column(columnDefinition = "TEXT")
    val content: String,

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
        name = "posts_media",
        joinColumns = [JoinColumn(name = "post_id")]
    )
    @Column(name = "media_value")
    val media: List<String>? = emptyList(),


    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val comments: MutableList<Comment>?,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val likes: MutableList<PostLike>?,

    @Column(name = "number_of_likes")
    val numberOfLikes: Long?,

    @Column(name = "number_of_comments")
    val numberOfComments: Long?,

    @Column(name = "is_featured")
    val isFeatured: Boolean,

    val approved: Boolean,

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "post_tags",
        joinColumns = [JoinColumn(name = "post_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    val tags: MutableSet<Tag> = mutableSetOf(),
)
