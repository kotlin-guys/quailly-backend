package ru.kpfu.itis.quailly.egg.domain.swipe

import ru.kpfu.itis.quailly.egg.domain.model.SwipeDirection

data class SwipeData(
    val direction: SwipeDirection,
    val merchandiseId: Long,
    val swiperId: Long
)