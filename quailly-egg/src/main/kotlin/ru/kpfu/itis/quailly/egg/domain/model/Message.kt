package ru.kpfu.itis.quailly.egg.domain.model

import java.time.ZonedDateTime

data class Message(
    val id: Long? = null,
    val created: ZonedDateTime,
    val text: String,
    val sender: Account,
    val dialog: Dialog
)