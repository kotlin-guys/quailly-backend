package ru.kpfu.itis.quailly.egg.generator.token

import org.springframework.stereotype.Component
import java.util.*

@Component
class UuidTokenGenerator(private val tokenPrefix: String = "Bearer ") : TokenGenerator {

    override fun generate() = tokenPrefix + UUID.randomUUID().toString()

}
