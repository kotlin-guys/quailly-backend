package ru.kpfu.itis.quailly.egg.security.token

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class TokenAuthentication(
    val token: String
) : Authentication {
    private var isAuthenticated: Boolean? = null
    lateinit var userDetails: TokenUserDetails

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = userDetails.authorities

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.isAuthenticated = isAuthenticated
    }

    override fun getName(): String = userDetails.username

    override fun getCredentials(): UserDetails = userDetails

    override fun getPrincipal(): Long = userDetails.accountId

    override fun isAuthenticated(): Boolean = isAuthenticated ?: false

    override fun getDetails(): UserDetails = userDetails
}