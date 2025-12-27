package com.ufc.jornal.repository

import com.ufc.jornal.domain.Tag
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TagRepository: JpaRepository<Tag, Long> {
    fun findByNameIn(names: Collection<String>): List<Tag>

    fun findByName(name: String): Tag?
}