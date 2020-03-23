package ru.kpfu.itis.quailly.egg

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class QuaillyEggApplication

fun main(args: Array<String>) {
    runApplication<QuaillyEggApplication>(*args)
}
