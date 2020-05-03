package ru.kpfu.itis.quailly.egg.security.token

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ru.kpfu.itis.quailly.egg.domain.model.Account

class TokenUserDetails(private val account: Account) : UserDetails {
    val accountId: Long = account.id ?: error("Authenticated user should have account id.")

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf()

    override fun isEnabled(): Boolean = account.emailVerified ?: true

    override fun getUsername(): String = account.email

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String? = account.password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}