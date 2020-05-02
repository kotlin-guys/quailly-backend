package ru.kpfu.itis.quailly.egg.repository.jooq

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.kpfu.itis.quailly.egg.domain.model.Account
import java.time.ZonedDateTime

@SpringBootTest
internal class JooqAccountRepositoryTest {

    @Autowired
    private lateinit var jooqAccountRepository: JooqAccountRepository

    @Test
    fun `test create`() {
        val account = Account(email = "", registrationDateTime = ZonedDateTime.now(), familyName = "", firstName = "")
        val saved = jooqAccountRepository.create(account)
        assertEquals(account.email, saved.email)
    }
}