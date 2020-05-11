package ru.kpfu.itis.quailly.egg.repository.api

import ru.kpfu.itis.quailly.egg.domain.model.MerchandiseCategory

interface MerchandiseCategoryRepository {
    fun getAll(): List<MerchandiseCategory>
}