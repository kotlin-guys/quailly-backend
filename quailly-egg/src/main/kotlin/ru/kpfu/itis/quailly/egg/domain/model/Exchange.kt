package ru.kpfu.itis.quailly.egg.domain.model

import java.time.ZonedDateTime

data class Exchange(
    val id: Long? = null,
    val publicationDateTime: ZonedDateTime,
    val authorId: Long,
    val exchangeStatus: ExchangeStatus,
    val firstMerchandiseId: Long,
    val secondMerchandiseId: Long
)

enum class ExchangeStatus {
    INITIATED,
    COMMUNICATION_PENDING,
    SUCCESS
}
