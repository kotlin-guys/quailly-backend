package ru.kpfu.itis.quailly.egg.domain.exchange

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import ru.kpfu.itis.quailly.egg.domain.model.Exchange
import ru.kpfu.itis.quailly.egg.repository.api.ExchangeRepository

@Service
class ExchangeService(private val exchangeRepository: ExchangeRepository) {

    fun getExchangesForAccount(accountId: Long): Flux<Exchange> =
        Flux.fromIterable(exchangeRepository.findExchangesForAccount(accountId))

}