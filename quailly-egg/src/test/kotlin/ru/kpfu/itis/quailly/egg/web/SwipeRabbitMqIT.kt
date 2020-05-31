package ru.kpfu.itis.quailly.egg.web

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import ru.kpfu.itis.quailly.egg.accountCreationData
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeRequest
import ru.kpfu.itis.quailly.egg.merchandise
import ru.kpfu.itis.quailly.egg.repository.api.AccountRepository
import ru.kpfu.itis.quailly.egg.repository.api.MerchandiseRepository
import ru.kpfu.itis.quailly.egg.retrieveToken
import ru.kpfu.itis.quailly.egg.swipeRequest
import java.util.*

@SpringBootTest(properties = ["SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/quailly"])
@AutoConfigureWebTestClient
internal class SwipeRabbitMqIT {

    @Autowired
    private lateinit var testClient: WebTestClient

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Autowired
    private lateinit var merchandiseRepository: MerchandiseRepository


    @Test
    @Disabled
    fun test() {
        val signInSuccess = testClient.retrieveToken(accountCreationData())
        val account = accountRepository.findByToken(signInSuccess.token)!!
        val signInSuccess2 = testClient.retrieveToken(accountCreationData())


        while (true) {
            val merchandise = merchandiseRepository.create(
                merchandise(UUID.randomUUID().toString(), account.id!!, 1, desiredCategoryIds = listOf(1))
            )
            swipe(swipeRequest(merchandiseId = merchandise.id!!), signInSuccess2.token)
                .expectStatus().isOk
        }
    }

    private fun swipe(request: SwipeRequest, token: String): WebTestClient.ResponseSpec {
        return testClient.post()
            .uri("/swipe")
            .header("Authorization", token)
            .bodyValue(request)
            .exchange()
    }
}