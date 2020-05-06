package ru.kpfu.itis.quailly.egg.security.token

import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class TokenServerSecurityContextRepository(
    private val authenticationManager: ReactiveAuthenticationManager
) : ServerSecurityContextRepository {

    companion object {
        private const val AUTHORIZATION_PREFIX = "Bearer"
    }

    override fun save(exchange: ServerWebExchange, context: SecurityContext): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {
        val token = exchange.request.headers.getFirst(AUTHORIZATION)

        return if (token != null && token.startsWith(AUTHORIZATION_PREFIX)) {
            authenticationManager.authenticate(TokenAuthentication(token))
                .map { SecurityContextImpl(it) }
        } else {
            Mono.empty()
        }
    }

}
