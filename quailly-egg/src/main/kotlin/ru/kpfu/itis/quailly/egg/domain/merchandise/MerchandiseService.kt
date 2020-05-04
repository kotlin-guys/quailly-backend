package ru.kpfu.itis.quailly.egg.domain.merchandise

import org.springframework.stereotype.Service
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import ru.kpfu.itis.quailly.egg.repository.api.MerchandiseRepository

@Service
class MerchandiseService(
    private val merchandiseRepository: MerchandiseRepository
) {

    fun createMerchandise(
        merchandiseCreationRequest: MerchandiseCreationRequest,
        authorId: Long
    ) {
        val newMerchandise = Merchandise(
            name = merchandiseCreationRequest.name,
            description = merchandiseCreationRequest.description,
            categoryId = merchandiseCreationRequest.categoryId,
            authorId = authorId,
            desiredCategoryIds = merchandiseCreationRequest.desiredCategoryIds
        )
        merchandiseRepository.create(newMerchandise)
    }

    fun findMerchandises(authorId: Long) = merchandiseRepository.getAllForAuthor(authorId)
}
