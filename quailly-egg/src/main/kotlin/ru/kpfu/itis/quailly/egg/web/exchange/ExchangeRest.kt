package ru.kpfu.itis.quailly.egg.web.exchange

import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import ru.kpfu.itis.quailly.egg.domain.exchange.ExchangeData
import ru.kpfu.itis.quailly.egg.domain.exchange.ExchangeService
import ru.kpfu.itis.quailly.egg.security.token.TokenAuthentication

@RequestMapping("/exchanges")
@RestController
class ExchangeRest(private val exchangeService: ExchangeService) {

    @GetMapping
    fun getMyExchanges(authentication: TokenAuthentication): Flux<ExchangeData> =
        exchangeService.getExchangesForAccount(authentication.principal)

    @PostMapping("/accept/{exchangeId}")
    fun acceptExchange(@PathVariable exchangeId: Long, authentication: TokenAuthentication) {
        exchangeService.acceptExchange(exchangeId, authentication.principal)
    }

    @PostMapping("/decline/{exchangeId}")
    fun declineExchange(@PathVariable exchangeId: Long, authentication: TokenAuthentication) {
        exchangeService.declineExchange(exchangeId, authentication.principal)
    }

}