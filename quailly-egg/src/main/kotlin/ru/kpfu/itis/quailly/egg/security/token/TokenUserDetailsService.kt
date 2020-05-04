package ru.kpfu.itis.quailly.egg.security.token

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import ru.kpfu.itis.quailly.egg.repository.api.AccountRepository

class TokenUserDetailsService(private val accountRepository: AccountRepository) : UserDetailsService {

    override fun loadUserByUsername(token: String): UserDetails? {
        val account = accountRepository.findByToken(token) ?: return null
        return TokenUserDetails(account)
    }
}