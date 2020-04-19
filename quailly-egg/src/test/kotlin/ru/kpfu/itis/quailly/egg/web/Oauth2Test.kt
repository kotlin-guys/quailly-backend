package ru.kpfu.itis.quailly.egg.web

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest
@SpringBootTest
class Oauth2Test {

    @Autowired
    private lateinit var webClient: WebTestClient

    @Test
    fun test() {

    }
}