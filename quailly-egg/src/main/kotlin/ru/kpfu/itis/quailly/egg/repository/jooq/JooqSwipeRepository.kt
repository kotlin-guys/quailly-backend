package ru.kpfu.itis.quailly.egg.repository.jooq

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.kpfu.itis.quailly.egg.domain.model.Swipe
import ru.kpfu.itis.quailly.egg.repository.api.SwipeRepository
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.Tables.MERCHANDISE
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.Tables.SWIPE
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.enums.SwipeDirection

@Repository
open class JooqSwipeRepository(private val jooq: DSLContext) : SwipeRepository {

    override fun create(entity: Swipe): Swipe =
        jooq.insertInto(SWIPE)
            .columns(SWIPE.SWIPER_ID, SWIPE.MERCHANDISE_ID, SWIPE.DIRECTION)
            .values(
                entity.accountId,
                entity.merchandiseId,
                SwipeDirection.valueOf(entity.direction.name)
            )
            .returning()
            .fetchOne()
            .into(Swipe::class.java)

    override fun findBackSwipeForMerchandise(merchandiseId: Long, accountId: Long): Swipe? {
        return jooq.select().from(SWIPE)
            .join(MERCHANDISE).on(SWIPE.MERCHANDISE_ID.eq(MERCHANDISE.ID))
            .where(
                MERCHANDISE.ID.`in`(
                    jooq.selectDistinct(MERCHANDISE.ID).from(SWIPE)
                        .join(MERCHANDISE).on(SWIPE.MERCHANDISE_ID.eq(MERCHANDISE.ID))
                        .where(
                            MERCHANDISE.AUTHOR_ID.eq(
                                jooq.select(MERCHANDISE.AUTHOR_ID).from(MERCHANDISE)
                                    .where(MERCHANDISE.ID.eq(merchandiseId))
                            )
                        )
                )
            )
            .fetchOne()
            ?.let { it.into(Swipe::class.java) }
    }
}