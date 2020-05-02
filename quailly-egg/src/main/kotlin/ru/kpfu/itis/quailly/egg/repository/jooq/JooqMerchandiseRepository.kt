package ru.kpfu.itis.quailly.egg.repository.jooq

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import ru.kpfu.itis.quailly.egg.repository.MerchandiseRepository

@Repository
open class JooqMerchandiseRepository(private val dsl: DSLContext) : MerchandiseRepository {

    override fun create(entity: Merchandise): Merchandise {
        TODO("Not yet implemented")
    }

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
}