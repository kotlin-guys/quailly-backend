package ru.kpfu.itis.quailly.egg.repository.jooq

import org.jooq.DSLContext
import org.springframework.stereotype.Repository
import ru.kpfu.itis.quailly.egg.domain.model.SwipeHistory
import ru.kpfu.itis.quailly.egg.repository.SwipeHistoryRepository
import ru.kpfu.itis.quailly.egg.repository.jooq.schema.Tables.SWIPE_HISTORY

@Repository
open class JooqSwipeHistoryRepository(private val jooq: DSLContext) : SwipeHistoryRepository {

    override fun create(entity: SwipeHistory): SwipeHistory =
        jooq.insertInto(SWIPE_HISTORY)
            .columns(SWIPE_HISTORY.SWIPERID, SWIPE_HISTORY.MERCHANDISEID, SWIPE_HISTORY.RESULT)
            .values(entity.accountId, entity.merchandiseId, entity.direction.name)
            .returning()
            .fetchOne()
            .into(SwipeHistory::class.java)

    override fun getById(id: Long): SwipeHistory {
        TODO("Not yet implemented")
    }

    override fun getAll(): Iterable<SwipeHistory> {
        TODO("Not yet implemented")
    }

    override fun update(entity: SwipeHistory): SwipeHistory {
        TODO("Not yet implemented")
    }

    override fun delete(entity: SwipeHistory) {
        TODO("Not yet implemented")
    }
}