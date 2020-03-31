package ru.kpfu.itis.quailly.egg.domain.model

import javax.persistence.*

@Entity
data class SwipeResult(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val accountId: Long,
    val merchandiseId: Long,
    @Enumerated(EnumType.STRING)
    val swipe: Swipe
)

enum class Swipe {
    LEFT,
    RIGHT
}
