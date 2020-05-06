package ru.kpfu.itis.quailly.egg.web.swipe

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeRequest
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeResult.Fail
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeResult.Success
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeService
import ru.kpfu.itis.quailly.egg.security.token.TokenAuthentication

@RestController
@RequestMapping("/swipe")
class SwipeRest(private val swipeService: SwipeService) {

    @PostMapping
    fun swipe(
        @RequestBody request: SwipeRequest,
        authentication: TokenAuthentication
    ): Mono<ResponseEntity<*>> =
        swipeService.swipe(request, authentication.principal).map {
            when (it) {
                is Success -> ResponseEntity.ok().build<Unit>()
                is Fail -> ResponseEntity.badRequest().body(it)
            }
        }

}