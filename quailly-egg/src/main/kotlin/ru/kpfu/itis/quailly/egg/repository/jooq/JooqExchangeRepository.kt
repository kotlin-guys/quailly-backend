package ru.kpfu.itis.quailly.egg.repository.jooq

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.kpfu.itis.quailly.egg.domain.model.Exchange
import ru.kpfu.itis.quailly.egg.repository.api.ExchangeRepository
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.Tables.EXCHANGE

@Repository
open class JooqExchangeRepository(private val jooq: DSLContext) : ExchangeRepository {

    override fun create(entity: Exchange): Exchange =
        jooq.insertInto(EXCHANGE)
            .columns(
                EXCHANGE.INITIATOR_ID,
                EXCHANGE.FIRST_MERCHANDISE_ID,
                EXCHANGE.SECOND_MERCHANDISE_ID,
                EXCHANGE.PUBLICATION_DATE_TIME,
                EXCHANGE.STATUS
            ).values(
                entity.authorId,
                entity.firstMerchandiseId,
                entity.secondMerchandiseId,
                entity.publicationDateTime.toOffsetDateTime(),
                entity.exchangeStatus.name
            )
            .returning()
            .fetchOne()
            .into(Exchange::class.java)

}