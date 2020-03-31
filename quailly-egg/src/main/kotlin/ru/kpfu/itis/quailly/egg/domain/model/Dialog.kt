package ru.kpfu.itis.quailly.egg.domain.model

import javax.persistence.*

@Entity
data class Dialog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val starter: Account,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val appender: Account,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val exchange: Exchange,
    @OneToMany
    val messages: List<Message>
)