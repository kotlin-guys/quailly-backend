package ru.kpfu.itis.quailly.egg.web

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import ru.kpfu.itis.quailly.egg.accountCreationData
import ru.kpfu.itis.quailly.egg.merchandiseCreationRequest
import ru.kpfu.itis.quailly.egg.retrieveToken
import java.util.*

@SpringBootTest(properties = ["SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/quailly"])
@AutoConfigureWebTestClient
internal class MerchandiseCreationIT {

    @Autowired
    private lateinit var testClient: WebTestClient

    @Test
    fun `merchandise not created - no token`() {
        val request = merchandiseCreationRequest("koshka kiwi")

        testClient.post()
            .uri("/merchandises")
            .bodyValue(request)
            .exchange()
            .expectStatus().isUnauthorized
            .expectBody().isEmpty
    }

    @Test
    fun `merchandise created successfully`() {
        val request = merchandiseCreationRequest("koshka kiwi")
        val signInSuccess = testClient.retrieveToken(accountCreationData())

        testClient.post()
            .uri("/merchandises")
            .header("Authorization", signInSuccess.token)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.id").exists()
            .jsonPath("$.authorId").exists()
            .jsonPath("$.name").isEqualTo(request.name)
    }

    @Test
    fun `merchandise didn't created, token doesn't identify account`() {
        val request = merchandiseCreationRequest("koshka kiwi")

        testClient.post()
            .uri("/merchandises")
            .header("Authorization", UUID.randomUUID().toString())
            .bodyValue(request)
            .exchange()
            .expectStatus().isUnauthorized
            .expectBody().isEmpty

    }

    @Test
    fun `merchandise created successfully with desired categories`() {
        val request = merchandiseCreationRequest("koshka kiwi", desiredCategoryIds = listOf(3, 11))
        val signInSuccess = testClient.retrieveToken(accountCreationData())

        testClient.post()
            .uri("/merchandises")
            .header("Authorization", signInSuccess.token)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.id").exists()
            .jsonPath("$.authorId").exists()
            .jsonPath("$.name").isEqualTo(request.name)

    }

}
