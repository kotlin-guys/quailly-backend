package ru.kpfu.itis.quailly.egg.web.swipe

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeData
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeRequest
import ru.kpfu.itis.quailly.egg.messaging.SwipeProducer
import ru.kpfu.itis.quailly.egg.security.token.TokenAuthentication

@RestController
@RequestMapping("/swipe")
class SwipeRest(private val swipeProducer: SwipeProducer) {

    @PostMapping
    fun swipe(
        @RequestBody request: SwipeRequest,
        authentication: TokenAuthentication
    ): Mono<ResponseEntity<*>> {
        return Mono.fromCallable {
            swipeProducer.sendSwipe(
                SwipeData(
                    direction = request.direction,
                    merchandiseId = request.merchandiseId,
                    swiperId = authentication.principal
                )
            )
            ResponseEntity.ok().build<Unit>()
        }
    }

}