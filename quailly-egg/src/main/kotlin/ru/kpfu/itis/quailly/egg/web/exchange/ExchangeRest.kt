package ru.kpfu.itis.quailly.egg.web.exchange

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import ru.kpfu.itis.quailly.egg.domain.exchange.ExchangeService
import ru.kpfu.itis.quailly.egg.domain.model.Exchange
import ru.kpfu.itis.quailly.egg.security.token.TokenAuthentication

@RequestMapping("/exchanges")
@RestController
class ExchangeRest(private val exchangeService: ExchangeService) {

    @GetMapping
    fun getMyExchanges(authentication: TokenAuthentication): Flux<Exchange> =
        exchangeService.getExchangesForAccount(authentication.principal)

}