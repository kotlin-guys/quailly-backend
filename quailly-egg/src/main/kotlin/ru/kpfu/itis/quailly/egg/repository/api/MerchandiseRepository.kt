package ru.kpfu.itis.quailly.egg.repository.api

import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import ru.kpfu.itis.quailly.egg.repository.CreatableRepository
import ru.kpfu.itis.quailly.egg.repository.SearchableRepository

interface MerchandiseRepository : CreatableRepository<Merchandise>, SearchableRepository<Merchandise, Long> {

    fun getAllForAuthor(authorId: Long): List<Merchandise>

    fun getNextMerchandisesForReview(limit: Long, authorId: Long): List<Merchandise>
}