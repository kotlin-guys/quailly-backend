package ru.kpfu.itis.quailly.egg.repository.jooq

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.kpfu.itis.quailly.egg.domain.model.MerchandiseCategory
import ru.kpfu.itis.quailly.egg.repository.api.MerchandiseCategoryRepository
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.Tables

@Repository
class JooqMerchandiseCategoryRepository(private val jooq: DSLContext) : MerchandiseCategoryRepository {

    override fun getById(id: Long): MerchandiseCategory {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<MerchandiseCategory> =
        jooq.selectFrom(Tables.MERCHANDISE_CATEGORY)
            .fetch()
            .into(MerchandiseCategory::class.java)
}