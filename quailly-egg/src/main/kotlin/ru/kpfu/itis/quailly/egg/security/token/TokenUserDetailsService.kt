package ru.kpfu.itis.quailly.egg.security.token

import org.springframework.context.annotation.Primary
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import ru.kpfu.itis.quailly.egg.repository.api.AccountRepository

@Primary
@Component
class TokenUserDetailsService(
    private val accountRepository: AccountRepository
) : ReactiveUserDetailsService {

    override fun findByUsername(token: String): Mono<UserDetails> {
        val account = accountRepository.findByToken(token)
        return Mono.justOrEmpty(account?.let { TokenUserDetails(account) })
    }
}
