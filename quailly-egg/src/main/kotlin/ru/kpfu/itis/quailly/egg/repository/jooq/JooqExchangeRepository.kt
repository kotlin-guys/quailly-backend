package ru.kpfu.itis.quailly.egg.repository.jooq

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.kpfu.itis.quailly.egg.domain.model.Exchange
import ru.kpfu.itis.quailly.egg.repository.api.ExchangeRepository
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.Tables.EXCHANGE
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.enums.ExchangeStatus
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.tables.Merchandise.MERCHANDISE

@Repository
open class JooqExchangeRepository(private val jooq: DSLContext) : ExchangeRepository {

    override fun create(entity: Exchange): Exchange =
        jooq.insertInto(EXCHANGE)
            .columns(
                EXCHANGE.INITIATOR_ID,
                EXCHANGE.FIRST_MERCHANDISE_ID,
                EXCHANGE.SECOND_MERCHANDISE_ID,
                EXCHANGE.PUBLICATION_DATE_TIME,
                EXCHANGE.EXCHANGE_STATUS
            ).values(
                entity.initiatorId,
                entity.firstMerchandiseId,
                entity.secondMerchandiseId,
                entity.publicationDateTime,
                ExchangeStatus.valueOf(entity.exchangeStatus.name)
            )
            .returning()
            .fetchOne()
            .into(Exchange::class.java)

    override fun findExchangesForAccount(accountId: Long): List<Exchange> {
        val first = MERCHANDISE.`as`("first")
        val second = MERCHANDISE.`as`("second")
        return jooq
            .select()
            .from(EXCHANGE)
            .join(first)
            .on(first.ID.eq(EXCHANGE.FIRST_MERCHANDISE_ID))
            .join(second)
            .on(second.ID.eq(EXCHANGE.SECOND_MERCHANDISE_ID))
            .where((first.AUTHOR_ID.eq(accountId).or(second.AUTHOR_ID.eq(accountId))))
            .and(EXCHANGE.EXCHANGE_STATUS.eq(ExchangeStatus.COMMUNICATION_PENDING))
            .fetchInto(Exchange::class.java)
    }

    override fun getById(id: Long): Exchange? =
        jooq.selectFrom(EXCHANGE)
            .where(EXCHANGE.ID.eq(id))
            .fetchOne()
            ?.let { it.into(Exchange::class.java) }

    override fun update(entity: Exchange): Exchange =
        jooq.update(EXCHANGE)
            .set(EXCHANGE.FIRST_ACCEPTED, entity.firstAccepted)
            .set(EXCHANGE.SECOND_ACCEPTED, entity.secondAccepted)
            .set(EXCHANGE.EXCHANGE_STATUS, ExchangeStatus.valueOf(entity.exchangeStatus.name))
            .returning()
            .fetchOne()
            .into(Exchange::class.java)

}
