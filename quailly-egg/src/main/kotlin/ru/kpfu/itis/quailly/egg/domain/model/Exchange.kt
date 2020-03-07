package ru.kpfu.itis.quailly.egg.domain.model

import javax.persistence.*

@Entity
data class Exchange(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val firstMerchandise: Merchandise,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val secondMerchandise: Merchandise
)
