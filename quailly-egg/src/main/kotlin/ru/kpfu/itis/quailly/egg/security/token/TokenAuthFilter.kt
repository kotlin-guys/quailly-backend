package ru.kpfu.itis.quailly.egg.security.token

import org.springframework.security.core.context.ReactiveSecurityContextHolder.getContext
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class TokenAuthFilter : WebFilter {

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun filter(
        exchange: ServerWebExchange,
        chain: WebFilterChain
    ): Mono<Void> = with(exchange.request.headers[AUTHORIZATION_HEADER]) {
        getContext().map { it.authentication = this?.let { TokenAuthentication(it.first()) } }
        chain.filter(exchange)
    }
}
