package ru.kpfu.itis.quailly.egg.repository.jooq

import org.jooq.DSLContext
import org.jooq.impl.DSL
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
        val m2 = MERCHANDISE.`as`("m2")
        val m = MERCHANDISE.`as`("m")
        val s = SWIPE.`as`("s")

        return jooq
            .select()
            .from(s)
            .join(m2)
            .on(s.MERCHANDISE_ID.eq(m2.ID))
            .where(
                s.SWIPER_ID.eq(
                    DSL
                        .select(m.AUTHOR_ID)
                        .from(m)
                        .where(m.ID.eq(merchandiseId))
                ).and(m2.AUTHOR_ID.eq(accountId))
            ).and(s.DIRECTION.eq(SwipeDirection.RIGHT))
            .fetchOne()
            ?.let { it.into(Swipe::class.java) }
    }

    override fun findSwipesForSwiperAndMerchandise(swiperId: Long, merchandiseId: Long): List<Swipe> =
        jooq.selectFrom(SWIPE)
            .where(SWIPE.SWIPER_ID.eq(swiperId))
            .and(SWIPE.MERCHANDISE_ID.eq(merchandiseId))
            .fetchInto(Swipe::class.java)
}