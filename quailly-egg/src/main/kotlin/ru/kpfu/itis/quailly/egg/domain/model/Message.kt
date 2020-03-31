package ru.kpfu.itis.quailly.egg.domain.model

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
data class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    val created: ZonedDateTime,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val sender: Account,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val receiver: Account,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val exchange: Exchange
)