package com.ufc.jornal.repository

import com.ufc.jornal.domain.Post
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PostRepository: JpaRepository<Post, Long> {
    @Query("""
        SELECT DISTINCT p FROM Post p
        LEFT JOIN p.tags t
        WHERE (:authorCode IS NULL OR p.author.code = :authorCode)
          AND (:query IS NULL OR LOWER(p.content) LIKE LOWER(CONCAT('%', :query, '%')))
          AND (:tags IS NULL OR t.name IN :tags)
          AND p.approved = true
    """)
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