package ru.kpfu.itis.quailly.egg.web

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import ru.kpfu.itis.quailly.egg.accountCreationData
import ru.kpfu.itis.quailly.egg.merchandise
import ru.kpfu.itis.quailly.egg.repository.api.AccountRepository
import ru.kpfu.itis.quailly.egg.repository.api.MerchandiseRepository
import ru.kpfu.itis.quailly.egg.retrieveToken

@SpringBootTest(properties = ["SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/quailly"])
@AutoConfigureWebTestClient
internal class MerchandiseForSwipeIT {

    @Autowired
    private lateinit var testClient: WebTestClient

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Autowired
    private lateinit var merchandiseRepository: MerchandiseRepository

    @Test
    fun `no merchandises for swipe`() {
        val signInSuccess = testClient.retrieveToken(accountCreationData())
        val account = accountRepository.findByToken(signInSuccess.token)!!

        val merchandisesForSwipe = merchandiseRepository.getNextMerchandisesForReview(1, account.id!!)
        assertEquals(0, merchandisesForSwipe.size)

    }

    @Test
    fun `merchandises of account able to get for swiping`() {
        val signInSuccess = testClient.retrieveToken(accountCreationData())
        val account = accountRepository.findByToken(signInSuccess.token)!!

        val merchandisesOfAccount = (1..10).map {
            merchandiseRepository.create(
                merchandise(
                    "Статуя наруто $it", account.id!!, desiredCategoryIds = listOf(it.toLong())
                )
            )
        }

        val merchandisesForSwipe = merchandiseRepository.getNextMerchandisesForReview(1000, account.id!!)
        merchandisesForSwipe.forEach {
            assertFalse(it in merchandisesOfAccount)
            println(it)
        }
        assertEquals(merchandisesForSwipe.size, merchandisesForSwipe.map { it.id }.toSet().size)
    }
}