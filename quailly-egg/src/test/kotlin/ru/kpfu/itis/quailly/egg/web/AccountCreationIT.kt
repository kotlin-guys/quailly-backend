package ru.kpfu.itis.quailly.egg.web

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import ru.kpfu.itis.quailly.egg.accountCreationData
import ru.kpfu.itis.quailly.egg.domain.account.AccountCreationData
import ru.kpfu.itis.quailly.egg.repository.api.AccountRepository

@SpringBootTest(properties = ["SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/quailly"])
@AutoConfigureWebTestClient
internal class AccountCreationIT {

    @Autowired
    private lateinit var testClient: WebTestClient

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Test
    fun `account created`() {
        val data = accountCreationData("yakuza")
        tokenRetrieved(data)

        val createdAccount = accountRepository.findByEmail(data.email)!!
        assertEquals(createdAccount.email, data.email)
        assertEquals(createdAccount.emailVerified, data.emailVerified)
        assertEquals(createdAccount.firstName, data.name)
        assertEquals(createdAccount.pictureUrl, data.pictureUrl)
        assertEquals(createdAccount.locale, data.locale)
        assertEquals(createdAccount.givenName, data.givenName)
    }

    @Test
    fun `signed in`() {
        val data = accountCreationData("yakuza")
        tokenRetrieved(data)
        tokenRetrieved(data)

        val createdAccount = accountRepository.findByEmail(data.email)!!
        assertEquals(createdAccount.email, data.email)
        assertEquals(createdAccount.emailVerified, data.emailVerified)
        assertEquals(createdAccount.firstName, data.name)
        assertEquals(createdAccount.pictureUrl, data.pictureUrl)
        assertEquals(createdAccount.locale, data.locale)
        assertEquals(createdAccount.givenName, data.givenName)
    }

    private fun tokenRetrieved(data: AccountCreationData): WebTestClient.BodyContentSpec =
        testClient.post()
            .uri("/accounts")
            .bodyValue(data)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.token").exists()
}