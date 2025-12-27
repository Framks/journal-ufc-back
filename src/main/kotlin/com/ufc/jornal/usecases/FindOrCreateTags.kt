package com.ufc.jornal.usecases

import com.ufc.jornal.domain.Tag
import com.ufc.jornal.repository.TagRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class FindOrCreateTags(
    private val tagRepository: TagRepository
) {

    @Transactional
    fun findOrCreate(tagNames: List<String>): Set<Tag> {

        val normalizedNames = tagNames
            .map { it.trim().lowercase() }
            .distinct()

        val existingTags = tagRepository.findByNameIn(normalizedNames)
        val existingNames = existingTags.map { it.name }.toSet()

        val newTags = normalizedNames
            .filterNot { it in existingNames }
            .map { Tag(name = it) }

        if (newTags.isNotEmpty()) {
            tagRepository.saveAll(newTags)
        }

        return (existingTags + newTags).toSet()
    }
}
