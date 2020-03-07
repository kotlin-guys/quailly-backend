package ru.kpfu.itis.quailly.egg.web.exchange

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kpfu.itis.quailly.egg.domain.account.ExchangeService

@RequestMapping("/exchanges")
@RestController
class ExchangeRest(
    private val exchangeService: ExchangeService
) {

}