package ru.kpfu.itis.quailly.egg.security.token

import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class TokenAuthenticationProvider(
    private val userDetailsService: ReactiveUserDetailsService
) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val tokenAuthentication = authentication as TokenAuthentication
        val details = userDetailsService.findByUsername(tokenAuthentication.token)

        return details.map {
            if (it != null) {
                tokenAuthentication.userDetails = it as TokenUserDetails
                tokenAuthentication.isAuthenticated = true
            }
            tokenAuthentication
        }
    }

}