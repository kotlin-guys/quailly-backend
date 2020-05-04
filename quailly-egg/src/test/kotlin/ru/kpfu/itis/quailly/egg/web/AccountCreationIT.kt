package ru.kpfu.itis.quailly.egg.web

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient
import ru.kpfu.itis.quailly.egg.domain.account.AccountCreationData
import ru.kpfu.itis.quailly.egg.repository.api.AccountRepository

@WebFluxTest
//@SpringBootTest
class AccountCreationIT {

    @Autowired
    private lateinit var testClient: WebTestClient

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Test
    fun `account created`() {
        val data = AccountCreationData("email", true, "azat", "pict", "en", "mukh", "")

        testClient.post().bodyValue(data)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .xpath("$.token").exists()

        val createdAccount = accountRepository.findByEmail(data.email)!!
        assertEquals(createdAccount.email, data.email)
        assertEquals(createdAccount.emailVerified, data.emailVerified)
        assertEquals(createdAccount.firstName, data.name)
        assertEquals(createdAccount.pictureUrl, data.pictureUrl)
        assertEquals(createdAccount.locale, data.locale)
        assertEquals(createdAccount.givenName, data.givenName)
    }
}