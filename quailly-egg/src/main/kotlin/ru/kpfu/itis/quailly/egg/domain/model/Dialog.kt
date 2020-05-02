package ru.kpfu.itis.quailly.egg.domain.model

data class Dialog(
    val id: Long? = null,
    val starterId: Long,
    val appenderId: Long,
    val exchange: Exchange,
    val messages: List<Message>
)