package ru.kpfu.itis.quailly.egg.repository

import ru.kpfu.itis.quailly.egg.domain.model.Merchandise

interface MerchandiseRepository : CrudRepository<Merchandise, Long> {

    fun getAllForAuthor(authorId: Long): List<Merchandise>
}