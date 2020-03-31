package ru.kpfu.itis.quailly.egg.domain.model

import javax.persistence.*

@Entity
data class ExchangeDesire(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @OneToMany
    val categories: List<MerchantCategory>,
    @OneToMany
    val merchandises: List<Merchandise>
)