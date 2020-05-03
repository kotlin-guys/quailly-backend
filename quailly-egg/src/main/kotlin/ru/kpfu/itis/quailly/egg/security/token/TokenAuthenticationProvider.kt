package ru.kpfu.itis.quailly.egg.security.token

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService

class TokenAuthenticationProvider(private val userDetailsService: UserDetailsService) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val tokenAuthentication = authentication as TokenAuthentication
        val details = userDetailsService.loadUserByUsername(tokenAuthentication.token)
        return details.let {
            if (details != null) {
                tokenAuthentication.userDetails = details as TokenUserDetails
                tokenAuthentication.isAuthenticated = true
            }
            tokenAuthentication
        }
    }

    override fun supports(authentication: Class<*>): Boolean = authentication == TokenAuthentication::class
}