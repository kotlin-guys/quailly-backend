package ru.kpfu.itis.quailly.egg.domain.swipe

import ru.kpfu.itis.quailly.egg.domain.model.SwipeDirection

data class SwipeRequest(
    val direction: SwipeDirection,
    val merchandiseId: Long
)