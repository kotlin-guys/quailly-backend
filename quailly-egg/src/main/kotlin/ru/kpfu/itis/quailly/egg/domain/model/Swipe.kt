package ru.kpfu.itis.quailly.egg.domain.model


data class Swipe(
    val id: Long? = null,
    val accountId: Long,
    val merchandiseId: Long,
    val direction: SwipeDirection
)

enum class SwipeDirection {
    LEFT,
    RIGHT
}
