package ru.kpfu.itis.quailly.egg.repository.jooq

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.kpfu.itis.quailly.egg.account
import java.util.*

@SpringBootTest(properties = ["SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/quailly"])
internal class JooqAccountRepositoryTest {

    @Autowired
    private lateinit var jooqAccountRepository: JooqAccountRepository

    @Test
    fun `merchandise created successfully`() {
        val account = account(UUID.randomUUID().toString())
        val saved = jooqAccountRepository.create(account)
        val fromDb = jooqAccountRepository.findByEmail(account.email)!!


        assertEquals(account.email, saved.email)
        assertEquals(fromDb.email, saved.email)
    }
}