package ru.kpfu.itis.quailly.egg.domain.model

import java.time.OffsetDateTime

data class Exchange(
    val id: Long? = null,
    val publicationDateTime: OffsetDateTime,
    val initiatorId: Long,
    val exchangeStatus: ExchangeStatus,
    val firstMerchandiseId: Long,
    val secondMerchandiseId: Long
)

enum class ExchangeStatus {
    INITIATED,
    COMMUNICATION_PENDING,
    SUCCESS
}
