package ru.kpfu.itis.quailly.egg.domain.model

import javax.persistence.*

@Entity
data class Merchandise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val author: Account,
    val name: String,
    val description: String
)
