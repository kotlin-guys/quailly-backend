package ru.kpfu.itis.quailly.egg.domain.model

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
data class Exchange(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val publicationDateTime: ZonedDateTime,
    @Enumerated(EnumType.STRING)
    val exchangeStatus: ExchangeStatus,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val firstMerchandise: Merchandise,
    @ManyToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val secondMerchandise: Merchandise,
    @OneToMany(mappedBy = "exchange")
    val messages: List<Message>
)

enum class ExchangeStatus {
    INITIATED,
    COMMUNICATION_PENDING,
    SUCCESS
}
