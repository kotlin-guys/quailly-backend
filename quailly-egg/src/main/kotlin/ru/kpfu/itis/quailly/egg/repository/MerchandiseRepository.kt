package ru.kpfu.itis.quailly.egg.repository

import ru.kpfu.itis.quailly.egg.domain.model.Merchandise

interface MerchandiseRepository : CrudRepository<Merchandise, Long>