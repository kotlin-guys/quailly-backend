package ru.kpfu.itis.quailly.egg.domain.model

import javax.persistence.*

@Entity
data class SwipeResult(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @OneToOne
    val account: Account,
    @OneToOne
    val merchandise: Merchandise,
    @Enumerated(EnumType.STRING)
    val swipe: Swipe
)