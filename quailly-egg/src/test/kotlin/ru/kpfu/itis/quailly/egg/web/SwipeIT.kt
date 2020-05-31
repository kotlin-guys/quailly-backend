package ru.kpfu.itis.quailly.egg.web

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import ru.kpfu.itis.quailly.egg.accountCreationData
import ru.kpfu.itis.quailly.egg.domain.model.ExchangeStatus
import ru.kpfu.itis.quailly.egg.domain.model.SwipeDirection
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeData
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeRequest
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeService
import ru.kpfu.itis.quailly.egg.merchandise
import ru.kpfu.itis.quailly.egg.repository.api.AccountRepository
import ru.kpfu.itis.quailly.egg.repository.api.ExchangeRepository
import ru.kpfu.itis.quailly.egg.repository.api.MerchandiseRepository
import ru.kpfu.itis.quailly.egg.repository.api.SwipeRepository
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
    private lateinit var swipeService: SwipeService

    @Autowired
    private lateinit var merchandiseRepository: MerchandiseRepository

    @Autowired
    private lateinit var swipeRepository: SwipeRepository

    @Test
    fun `able to swipe merchandise with token in header`() {
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
    fun `1in result of mutual swipe exchange should be created`() {
        val signInSuccess = testClient.retrieveToken(accountCreationData())
        val account = accountRepository.findByToken(signInSuccess.token)!!
        val merchandise = merchandiseRepository.create(
            merchandise("Самакат", account.id!!, 1, desiredCategoryIds = listOf(2))
        )

        val signInSuccess2 = testClient.retrieveToken(accountCreationData())
        val account2 = accountRepository.findByToken(signInSuccess2.token)!!
        val merchandise2 = merchandiseRepository.create(
            merchandise("Телефон", account2.id!!, 2, desiredCategoryIds = listOf(1))
        )

        swipeService.swipe(SwipeData(SwipeDirection.RIGHT, merchandise2.id!!, account.id!!))
        swipeService.swipe(SwipeData(SwipeDirection.RIGHT, merchandise.id!!, account2.id!!))


        val exchanges = exchangeRepository.findExchangesForAccount(account.id!!)
        val exchanges2 = exchangeRepository.findExchangesForAccount(account2.id!!)
        assertEquals(1, exchanges.size)
        assertEquals(1, exchanges2.size)
        exchanges.forEachIndexed { index, exc ->
            assertEquals(exc, exchanges2[index])
        }
        val exchange = exchanges.first()
        assertEquals(account2.id!!, exchange.initiatorId)
        assertEquals(merchandise2.id!!, exchange.firstMerchandiseId)
        assertEquals(merchandise.id!!, exchange.secondMerchandiseId)
        assertEquals(ExchangeStatus.COMMUNICATION_PENDING, exchange.exchangeStatus)
    }

    @Test
    @Disabled
    fun `in result of mutual swipe exchange should be created`() {
        val signInSuccess = testClient.retrieveToken(accountCreationData())
        val account = accountRepository.findByToken(signInSuccess.token)!!
        val merchandise = merchandiseRepository.create(
            merchandise("Koshka kiwi", account.id!!, 1, desiredCategoryIds = listOf(2))
        )

        val signInSuccess2 = testClient.retrieveToken(accountCreationData())
        val account2 = accountRepository.findByToken(signInSuccess2.token)!!
        val merchandise2 = merchandiseRepository.create(
            merchandise("Samat", account2.id!!, 2, desiredCategoryIds = listOf(1))
        )

        val request = swipeRequest(merchandise2.id!!)
        val request2 = swipeRequest(merchandise.id!!)

        makeSwipe(request, signInSuccess.token)

        val swipe = swipeRepository.findSwipesForSwiperAndMerchandise(account.id!!, request.merchandiseId)
        var swipe2 = swipeRepository.findSwipesForSwiperAndMerchandise(account2.id!!, request2.merchandiseId)
        assertTrue(swipe.isNotEmpty())
        assertNotNull(swipe2.isEmpty())

        val backSwipe = swipeRepository.findBackSwipeForMerchandise(request.merchandiseId, account.id!!)
        assertNull(backSwipe)

        makeSwipe(request2, signInSuccess2.token)
        swipe2 = swipeRepository.findSwipesForSwiperAndMerchandise(account2.id!!, request2.merchandiseId)
        assertNotNull(swipe2.isNotEmpty())

        val backSwipe2 = swipeRepository.findBackSwipeForMerchandise(request2.merchandiseId, account2.id!!)
        assertNotNull(backSwipe2)

        val exchanges = exchangeRepository.findExchangesForAccount(account.id!!)
        val exchanges2 = exchangeRepository.findExchangesForAccount(account2.id!!)
        assertEquals(1, exchanges.size)
        assertEquals(1, exchanges2.size)
        exchanges.forEachIndexed { index, exc ->
            assertEquals(exc, exchanges2[index])
        }
        val exchange = exchanges.first()
        assertEquals(account2.id!!, exchange.initiatorId)
        assertEquals(merchandise2.id!!, exchange.firstMerchandiseId)
        assertEquals(merchandise.id!!, exchange.secondMerchandiseId)
        assertEquals(ExchangeStatus.COMMUNICATION_PENDING, exchange.exchangeStatus)
    }

    private fun makeSwipe(request: SwipeRequest, token: String) {
        testClient.post()
            .uri("/swipe")
            .header("Authorization", token)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
            .expectBody().isEmpty
    }

    @CsvSource("LEFT,LEFT", "LEFT,RIGHT", "RIGHT,LEFT")
    @ParameterizedTest
    fun `exchange should be created only for right-right directions`(
        firstDirection: SwipeDirection, secondDirection: SwipeDirection
    ) {
        val signInSuccess = testClient.retrieveToken(accountCreationData())
        val account = accountRepository.findByToken(signInSuccess.token)!!
        val merchandise = merchandiseRepository.create(
            merchandise("Koshka kiwi", account.id!!, 1, desiredCategoryIds = listOf(1))
        )

        val signInSuccess2 = testClient.retrieveToken(accountCreationData())
        val account2 = accountRepository.findByToken(signInSuccess2.token)!!
        val merchandise2 = merchandiseRepository.create(
            merchandise("Samat", account2.id!!, 2, desiredCategoryIds = listOf(1))
        )

        val request = swipeRequest(merchandise2.id!!, firstDirection)
        testClient.post()
            .uri("/swipe")
            .header("Authorization", signInSuccess.token)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk
            .expectBody().isEmpty

        val request2 = swipeRequest(merchandise.id!!, secondDirection)
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