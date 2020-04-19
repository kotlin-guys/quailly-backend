package ru.kpfu.itis.quailly.egg.domain.account

data class AccountVerificationData(
    val email: String,
    val emailVerified: Boolean,
    val name: String,
    val pictureUrl: String,
    val locale: String,
    val familyName: String,
    val givenName: String
)