package ru.kpfu.itis.quailly.egg.domain.exchange

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import ru.kpfu.itis.quailly.egg.domain.model.Exchange
import ru.kpfu.itis.quailly.egg.domain.model.ExchangeStatus.DECLINED
import ru.kpfu.itis.quailly.egg.domain.model.ExchangeStatus.SUCCESS
import ru.kpfu.itis.quailly.egg.repository.api.ExchangeRepository
import ru.kpfu.itis.quailly.egg.repository.api.MerchandiseRepository

@Service
class ExchangeService(
    private val exchangeRepository: ExchangeRepository,
    private val merchandiseRepository: MerchandiseRepository
) {

    fun getExchangesForAccount(accountId: Long): Flux<ExchangeData> =
        Flux.fromIterable(exchangeRepository.findExchangesForAccount(accountId))
            .map {
                ExchangeData(
                    id = it.id,
                    publicationDateTime = it.publicationDateTime,
                    exchangeStatus = it.exchangeStatus,
                    firstAccepted = it.firstAccepted,
                    secondAccepted = it.secondAccepted,
                    firstMerchandise = merchandiseRepository.getById(it.firstMerchandiseId)
                        ?: error("First merchandise is null"),
                    secondMerchandise = merchandiseRepository.getById(it.secondMerchandiseId)
                        ?: error("Second merchandise is null")
                )
            }

    fun acceptExchange(exchangeId: Long, accountId: Long) {
        updateExchange(exchangeId, accountId, true)
    }

    fun declineExchange(exchangeId: Long, accountId: Long) {
        updateExchange(exchangeId, accountId, false)

    }

    private fun updateExchange(exchangeId: Long, accountId: Long, accepted: Boolean) {
        val exchange = exchangeRepository.getById(exchangeId) ?: return
        val firstAccepted = if (exchange.initiatorId != accountId) accepted else exchange.firstAccepted
        val secondAccepted = if (exchange.initiatorId == accountId) accepted else exchange.secondAccepted
        exchangeRepository.update(
            Exchange(
                id = exchange.id,
                publicationDateTime = exchange.publicationDateTime,
                initiatorId = exchange.initiatorId,
                firstMerchandiseId = exchange.firstMerchandiseId,
                secondMerchandiseId = exchange.secondMerchandiseId,
                firstAccepted = firstAccepted,
                secondAccepted = secondAccepted,
                exchangeStatus = if (firstAccepted && secondAccepted) SUCCESS else DECLINED
            )
        )
    }

}