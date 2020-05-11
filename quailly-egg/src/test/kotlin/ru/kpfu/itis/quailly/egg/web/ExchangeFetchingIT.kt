package ru.kpfu.itis.quailly.egg.web

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import ru.kpfu.itis.quailly.egg.repository.api.ExchangeRepository

@SpringBootTest(properties = ["SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/quailly"])
@AutoConfigureWebTestClient
internal class ExchangeFetchingIT {

    @Autowired
    private lateinit var testClient: WebTestClient

    @Autowired
    private lateinit var exchangeRepository: ExchangeRepository

    @Test
    fun test() {

    }
}
