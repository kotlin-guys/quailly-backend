package ru.kpfu.itis.quailly.egg.config

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import ru.kpfu.itis.quailly.egg.security.token.TokenAuthFilter
import ru.kpfu.itis.quailly.egg.security.token.TokenAuthenticationProvider

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
open class SecurityConfiguration(
    private val securityFilter: TokenAuthFilter,
    private val authenticationProvider: TokenAuthenticationProvider
) : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/accounts/verify", "/quailly-backend/**", "/swagger-ui.html").permitAll()
            .anyRequest().permitAll()
            .and()
            .sessionManagement().disable()
            .csrf().disable()
            .formLogin().disable()
            .authenticationProvider(authenticationProvider)
            .addFilter(securityFilter)
    }
}