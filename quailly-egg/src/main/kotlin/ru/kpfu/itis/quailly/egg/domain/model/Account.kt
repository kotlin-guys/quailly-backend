package ru.kpfu.itis.quailly.egg.domain.model

import javax.persistence.*

@Entity
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val username: String,
    val phoneNumber: String,
    @OneToMany
    val merchandises: List<Merchandise>
)
