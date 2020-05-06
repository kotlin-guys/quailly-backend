package ru.kpfu.itis.quailly.egg.web

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import ru.kpfu.itis.quailly.egg.accountCreationData
import ru.kpfu.itis.quailly.egg.merchandise
import ru.kpfu.itis.quailly.egg.repository.api.AccountRepository
import ru.kpfu.itis.quailly.egg.repository.api.ExchangeRepository
import ru.kpfu.itis.quailly.egg.repository.api.MerchandiseRepository
import ru.kpfu.itis.quailly.egg.retrieveToken
import ru.kpfu.itis.quailly.egg.swipeRequest

@SpringBootTest(properties = ["SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/quailly"])
@AutoConfigureWebTestClient
internal class SwipeIT {

    @Autowired
    private lateinit var testClient: WebTestClient

    @Autowired
    private lateinit var exchangeRepository: ExchangeRepository

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Autowired
    private lateinit var merchandiseRepository: MerchandiseRepository

    @Test
    fun text() {
        val signInSuccess = testClient.retrieveToken(accountCreationData())
        val account = accountRepository.findByToken(signInSuccess.token)!!
        val merchandise = merchandiseRepository.create(merchandise("Koshka kiwi", account.id!!))

        val request = swipeRequest(merchandise.id!!)
        testClient.post()
            .uri("/swipe")
            .header("Authorization", signInSuccess.token)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
            .expectBody().isEmpty

    }

    @Test
    fun test() {
        val signInSuccess = testClient.retrieveToken(accountCreationData())
        val account = accountRepository.findByToken(signInSuccess.token)!!
        val merchandise =
            merchandiseRepository.create(merchandise("Koshka kiwi", account.id!!, 1, desiredCategoryIds = listOf(2)))

        val request = swipeRequest(merchandise.id!!)
        testClient.post()
            .uri("/swipe")
            .header("Authorization", signInSuccess.token)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
            .expectBody().isEmpty

        val signInSuccess2 = testClient.retrieveToken(accountCreationData())
        val account2 = accountRepository.findByToken(signInSuccess2.token)!!
        val merchandise2 =
            merchandiseRepository.create(merchandise("Samat", account2.id!!, 2, desiredCategoryIds = listOf(1)))

        val request2 = swipeRequest(merchandise2.id!!)
        testClient.post()
            .uri("/swipe")
            .header("Authorization", signInSuccess2.token)
            .bodyValue(request2)
            .exchange()
            .expectStatus().isOk
            .expectBody().isEmpty

        val exchanges = exchangeRepository.findExchangesForAccount(account.id!!)
        val exchanges2 = exchangeRepository.findExchangesForAccount(account2.id!!)
        assertEquals(1, exchanges.size)
        assertEquals(1, exchanges2.size)
        exchanges.forEachIndexed { index, exc ->
            assertEquals(exc, exchanges2[index])
        }
    }

    @Test
    fun test2() {
        val signInSuccess = testClient.retrieveToken(accountCreationData())
        val account = accountRepository.findByToken(signInSuccess.token)!!
        val merchandise =
            merchandiseRepository.create(merchandise("Koshka kiwi", account.id!!, 1, desiredCategoryIds = listOf(1)))

        val request = swipeRequest(merchandise.id!!)
        testClient.post()
            .uri("/swipe")
            .header("Authorization", signInSuccess.token)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
            .expectBody().isEmpty

        val signInSuccess2 = testClient.retrieveToken(accountCreationData())
        val account2 = accountRepository.findByToken(signInSuccess2.token)!!
        val merchandise2 =
            merchandiseRepository.create(merchandise("Samat", account2.id!!, 2, desiredCategoryIds = listOf(1)))

        val request2 = swipeRequest(merchandise2.id!!)
        testClient.post()
            .uri("/swipe")
            .header("Authorization", signInSuccess2.token)
            .bodyValue(request2)
            .exchange()
            .expectStatus().isOk
            .expectBody().isEmpty

        val exchanges = exchangeRepository.findExchangesForAccount(account.id!!)
        val exchanges2 = exchangeRepository.findExchangesForAccount(account2.id!!)
        assertEquals(0, exchanges.size)
        assertEquals(0, exchanges2.size)
    }
}