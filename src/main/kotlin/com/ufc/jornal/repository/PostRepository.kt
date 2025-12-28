package com.ufc.jornal.repository

import com.ufc.jornal.domain.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = ["author", "tags", "media"])
    @Query(
        value = """
        SELECT DISTINCT p FROM Post p
        WHERE (:authorCode IS NULL OR p.author.code = :authorCode)
          AND (:query IS NULL OR LOWER(p.content) LIKE LOWER(CONCAT('%', :query, '%')))
          AND p.approved = true
          AND p.deleted = false
    """,
        countQuery = """
        SELECT COUNT(DISTINCT p) FROM Post p
        WHERE p.approved = true
          AND p.deleted = false
    """
    )
    fun search(
        @Param("authorCode")
        authorCode: String?,

        @Param("query")
        query: String?,

        @Param("tags")
        tags: List<String>?,

        pageable: Pageable
    ): Page<Post>
}