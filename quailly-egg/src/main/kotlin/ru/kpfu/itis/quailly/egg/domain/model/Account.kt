package ru.kpfu.itis.quailly.egg.domain.model

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val username: String,
    val email: String,
    val password: String,
    val phoneNumber: String?,
    val registrationDateTime: ZonedDateTime,
    @OneToMany
    val merchandises: List<Merchandise>
)
