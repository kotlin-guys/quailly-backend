package ru.kpfu.itis.quailly.egg.domain.model

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
data class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val username: String,
    val email: String,
    val password: String,
    val phoneNumber: String?,
    val registrationDateTime: ZonedDateTime,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "author", orphanRemoval = true)
    val merchandises: List<Merchandise>
)
