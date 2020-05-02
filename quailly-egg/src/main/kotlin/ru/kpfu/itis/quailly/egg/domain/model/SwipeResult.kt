package ru.kpfu.itis.quailly.egg.domain.model


data class SwipeResult(
    val id: Long? = null,
    val accountId: Long,
    val merchandiseId: Long,
    val swipe: Swipe
)

enum class Swipe {
    LEFT,
    RIGHT
}
