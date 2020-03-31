package ru.kpfu.itis.quailly.egg.domain.model

import javax.persistence.*

@Entity
data class MerchantCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val name: String,
    @ManyToOne
    val merchandise: ExchangeDesire
)
