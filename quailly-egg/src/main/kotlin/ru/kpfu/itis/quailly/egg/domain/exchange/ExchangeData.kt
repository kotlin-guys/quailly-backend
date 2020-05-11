package ru.kpfu.itis.quailly.egg.domain.exchange

import ru.kpfu.itis.quailly.egg.domain.model.ExchangeStatus
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import java.time.OffsetDateTime

data class ExchangeData(
    val id: Long? = null,
    val publicationDateTime: OffsetDateTime,
    val exchangeStatus: ExchangeStatus,
    val firstMerchandise: Merchandise,
    val secondMerchandise: Merchandise,
    val firstAccepted: Boolean,
    val secondAccepted: Boolean
)