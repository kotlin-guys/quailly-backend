package ru.kpfu.itis.quailly.egg.repository

import ru.kpfu.itis.quailly.egg.domain.model.Swipe

interface SwipeRepository : CrudRepository<Swipe, Long> {

    fun findSwipeForExchange(merchandiseId: Long, accountId: Long): Swipe?

}