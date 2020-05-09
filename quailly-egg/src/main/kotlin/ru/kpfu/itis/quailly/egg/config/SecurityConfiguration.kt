package ru.kpfu.itis.quailly.egg.config

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.ServerSecurityContextRepository

@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
open class SecurityConfiguration(
    private val contextRepository: ServerSecurityContextRepository,
    private val authenticationManager: ReactiveAuthenticationManager
) {

    @Bean
    open fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http.authorizeExchange()
            .pathMatchers(
                "/internal/info",
                "/accounts",
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/**",
                "/swagger-ui.html",
                "/webjars/**"
            ).permitAll()
            .anyExchange().authenticated().and()
            .cors().disable()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .securityContextRepository(contextRepository)
            .authenticationManager(authenticationManager)
            .build()

}
