package ru.kpfu.itis.quailly.egg.domain.model

import java.time.ZonedDateTime

data class Exchange(
    val id: Long? = null,
    val publicationDateTime: ZonedDateTime,
    val exchangeStatus: ExchangeStatus,
    val firstMerchandise: Merchandise,
    val secondMerchandise: Merchandise,
    val dialog: Dialog
)

enum class ExchangeStatus {
    INITIATED,
    COMMUNICATION_PENDING,
    SUCCESS
}
