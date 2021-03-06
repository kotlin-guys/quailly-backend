package ru.kpfu.itis.quailly.egg.domain.model

import java.time.OffsetDateTime

data class Account(
    val id: Long? = null,
    val username: String? = null,
    val email: String,
    val emailVerified: Boolean? = null,
    val firstName: String,
    val pictureUrl: String? = null,
    val locale: String? = null,
    val familyName: String,
    val givenName: String? = null,
    val password: String? = null,
    val phoneNumber: String? = null,
    val lastVisit: OffsetDateTime,
    val registrationDatetime: OffsetDateTime,
    val token: String? = null,
    val merchandises: List<Merchandise>? = null
)
