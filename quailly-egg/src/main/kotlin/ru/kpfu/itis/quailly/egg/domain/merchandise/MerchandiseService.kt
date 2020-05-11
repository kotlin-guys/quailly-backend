package ru.kpfu.itis.quailly.egg.domain.merchandise

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import ru.kpfu.itis.quailly.egg.repository.api.MerchandiseRepository
import java.time.OffsetDateTime

@Service
class MerchandiseService(
    private val merchandiseRepository: MerchandiseRepository
) {

    fun createMerchandise(
        merchandiseCreationRequest: MerchandiseCreationRequest,
        authorId: Long
    ): Mono<Merchandise> {
        val newMerchandise = Merchandise(
            name = merchandiseCreationRequest.name,
            description = merchandiseCreationRequest.description,
            categoryId = merchandiseCreationRequest.categoryId,
            authorId = authorId,
            pictureUrl = merchandiseCreationRequest.pictureUrl,
            desiredCategoryIds = merchandiseCreationRequest.desiredCategoryIds,
            created = OffsetDateTime.now()
        )
        return Mono.just(merchandiseRepository.create(newMerchandise))
    }

    fun findMerchandises(authorId: Long): Flux<Merchandise> =
        Flux.fromIterable(merchandiseRepository.getAllForAuthor(authorId))

    fun findMerchandiseById(merchandiseId: Long): Mono<Merchandise> =
        Mono.justOrEmpty(merchandiseRepository.getById(merchandiseId))
}
