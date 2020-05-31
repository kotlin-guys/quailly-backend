package ru.kpfu.itis.quailly.egg.web

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import ru.kpfu.itis.quailly.egg.*
import ru.kpfu.itis.quailly.egg.repository.api.AccountRepository
import ru.kpfu.itis.quailly.egg.repository.api.ExchangeRepository

@SpringBootTest(properties = ["SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/quailly"])
@AutoConfigureWebTestClient
internal class ExchangeFetchingIT {

    @Autowired
    private lateinit var testClient: WebTestClient

    @Autowired
    private lateinit var exchangeRepository: ExchangeRepository

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Test
    fun `token retrieving success`() {
        val signInSuccess = testClient.retrieveToken(accountCreationData())
        fetchMerchandises(signInSuccess.token)
            .expectBody()
            .json("[]")
    }

    @Test
    fun `create exchange works fine`() {
        val signInSuccess = testClient.retrieveToken(accountCreationData())
        val signInSuccess1 = testClient.retrieveToken(accountCreationData())
        val account = accountRepository.findByToken(signInSuccess.token)
        val m = testClient.createMerchandise(merchandiseCreationRequest("guf"), signInSuccess.token)
        val m1 = testClient.createMerchandise(merchandiseCreationRequest("basta"), signInSuccess1.token)

        exchangeRepository.create(exchange(m.id!!, m1.id!!, account?.id!!))
        fetchMerchandises(signInSuccess.token)
            .expectBody()
            .jsonPath("$.[0].firstMerchandise.id").isEqualTo(m.id!!)
            .jsonPath("$.[0].secondMerchandise.id").isEqualTo(m1.id!!)
    }

    private fun fetchMerchandises(token: String) = testClient
        .get().uri("/exchanges")
        .header("Authorization", token)
        .exchange()
        .expectStatus().isOk
}
