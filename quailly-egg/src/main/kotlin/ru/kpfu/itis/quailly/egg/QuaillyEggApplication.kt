package ru.kpfu.itis.quailly.egg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@SpringBootApplication
open class QuaillyEggApplication

fun main(args: Array<String>) {
    runApplication<QuaillyEggApplication>(*args)
}
