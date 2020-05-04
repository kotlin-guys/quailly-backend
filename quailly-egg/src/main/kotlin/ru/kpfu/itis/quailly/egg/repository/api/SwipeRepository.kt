package ru.kpfu.itis.quailly.egg.repository.api

import ru.kpfu.itis.quailly.egg.domain.model.Swipe
import ru.kpfu.itis.quailly.egg.repository.CreatableRepository

interface SwipeRepository : CreatableRepository<Swipe> {

    fun findSwipeForExchange(merchandiseId: Long, accountId: Long): Swipe?

}