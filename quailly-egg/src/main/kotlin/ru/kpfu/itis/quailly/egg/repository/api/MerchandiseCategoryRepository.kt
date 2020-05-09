package ru.kpfu.itis.quailly.egg.repository.api

import ru.kpfu.itis.quailly.egg.domain.model.MerchandiseCategory
import ru.kpfu.itis.quailly.egg.repository.SearchableRepository

interface MerchandiseCategoryRepository : SearchableRepository<MerchandiseCategory, Long>