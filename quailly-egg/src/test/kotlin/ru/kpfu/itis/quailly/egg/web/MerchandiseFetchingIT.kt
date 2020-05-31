package ru.kpfu.itis.quailly.egg.web

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import ru.kpfu.itis.quailly.egg.accountCreationData
import ru.kpfu.itis.quailly.egg.createMerchandise
import ru.kpfu.itis.quailly.egg.merchandiseCreationRequest
import ru.kpfu.itis.quailly.egg.retrieveToken

@SpringBootTest(properties = ["SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/quailly"])
@AutoConfigureWebTestClient
class MerchandiseFetchingIT {

    @Autowired
    private lateinit var testClient: WebTestClient

    @Test
    fun test() {
        val signInSuccess = testClient.retrieveToken(accountCreationData())

        fetchMyMerchandises(signInSuccess.token)
            .json("[]")
    }

    @Test
    fun `created merchandises fetched successfully`() {
        val signInSuccess = testClient.retrieveToken(accountCreationData())
        val m1 = testClient.createMerchandise(merchandiseCreationRequest("trap"), signInSuccess.token)
        val m2 = testClient.createMerchandise(merchandiseCreationRequest("cloud"), signInSuccess.token)
        val m3 = testClient.createMerchandise(merchandiseCreationRequest("scream"), signInSuccess.token)
        fetchMyMerchandises(signInSuccess.token)
            .jsonPath("$.[0].id").isEqualTo(m1.id!!)
            .jsonPath("$.[1].id").isEqualTo(m2.id!!)
            .jsonPath("$.[2].id").isEqualTo(m3.id!!)

    }

    private fun fetchMyMerchandises(token: String) =
        testClient.get()
            .uri("/merchandises")
            .header("Authorization", token)
            .exchange()
            .expectStatus().isOk
            .expectBody()
}