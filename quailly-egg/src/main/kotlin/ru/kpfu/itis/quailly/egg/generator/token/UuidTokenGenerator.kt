package ru.kpfu.itis.quailly.egg.generator.token

import org.springframework.stereotype.Component
import java.util.*

@Component
class UuidTokenGenerator : TokenGenerator {

    override fun generate() = UUID.randomUUID().toString()

}
