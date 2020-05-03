package ru.kpfu.itis.quailly.egg.repository.jooq

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import ru.kpfu.itis.quailly.egg.repository.MerchandiseRepository
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.tables.Merchandise.MERCHANDISE

@Repository
open class JooqMerchandiseRepository(private val jooq: DSLContext) : MerchandiseRepository {

    override fun create(entity: Merchandise): Merchandise =
        jooq.insertInto(MERCHANDISE)
            .columns(MERCHANDISE.NAME, MERCHANDISE.DESCRIPTION, MERCHANDISE.CATEGORY_ID, MERCHANDISE.AUTHOR_ID)
            .values(entity.name, entity.description, entity.categoryId, entity.authorId)
            .returning()
            .fetchOne()
            .into(Merchandise::class.java)

    override fun getById(id: Long): Merchandise {
        TODO("Not yet implemented")
    }

    override fun getAll(): Iterable<Merchandise> {
        TODO("Not yet implemented")
    }

    override fun update(entity: Merchandise): Merchandise {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Merchandise) {
        TODO("Not yet implemented")
    }

    override fun getAllForAuthor(authorId: Long): List<Merchandise> {
        return jooq.select().from(MERCHANDISE)
            .where(MERCHANDISE.AUTHOR_ID.eq(authorId))
            .fetch()
            .into(Merchandise::class.java)
    }
}