package ru.kpfu.itis.quailly.egg.config

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import ru.kpfu.itis.quailly.egg.security.token.TokenAuthFilter
import ru.kpfu.itis.quailly.egg.security.token.TokenAuthenticationProvider

@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
open class SecurityConfiguration(
    private val securityFilter: TokenAuthFilter,
    private val authenticationProvider: TokenAuthenticationProvider
) {

    @Bean
    open fun securityWebFilterChain(
        http: ServerHttpSecurity
    ): SecurityWebFilterChain {
        return http.authorizeExchange()
            .pathMatchers("/accounts", "/quailly-backend/**", "/swagger-ui.html").permitAll()
            .anyExchange().authenticated().and()
            .csrf().disable()
            .formLogin().disable()
            .authenticationManager(authenticationProvider)
            .addFilterAfter(securityFilter, SecurityWebFiltersOrder.FIRST)
            .build()


    }
}