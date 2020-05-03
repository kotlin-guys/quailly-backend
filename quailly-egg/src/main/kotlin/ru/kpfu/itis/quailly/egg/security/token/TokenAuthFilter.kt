package ru.kpfu.itis.quailly.egg.security.token

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class TokenAuthFilter : OncePerRequestFilter() {

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorization = request.getHeader(AUTHORIZATION_HEADER)
        if (authorization != null) {
            SecurityContextHolder.getContext().authentication = TokenAuthentication(authorization)
        }
        filterChain.doFilter(request, response)
    }
}