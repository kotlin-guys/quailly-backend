package ru.kpfu.itis.quailly.egg.web.internal

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RequestMapping("/internal/info")
@RestController
class QuaillyApplicationRest {

    @GetMapping
    fun quailly(): Mono<String> = Mono.just("quailly")
}
