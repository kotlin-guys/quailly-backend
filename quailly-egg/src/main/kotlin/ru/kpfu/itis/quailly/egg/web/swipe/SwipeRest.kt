package ru.kpfu.itis.quailly.egg.web.swipe

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeRequest
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeResult.Fail
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeResult.Success
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeService
import ru.kpfu.itis.quailly.egg.security.token.TokenAuthentication

@RestController
@RequestMapping("/swipe")
class SwipeRest(private val swipeService: SwipeService) {

    @PostMapping
    fun swipe(request: SwipeRequest, authentication: TokenAuthentication): ResponseEntity<*> {
        return when (val swipeResult = swipeService.swipe(request, authentication.principal)) {
            is Success -> ResponseEntity.ok().build<Unit>()
            is Fail -> ResponseEntity.badRequest().body(swipeResult)
        }
    }

}