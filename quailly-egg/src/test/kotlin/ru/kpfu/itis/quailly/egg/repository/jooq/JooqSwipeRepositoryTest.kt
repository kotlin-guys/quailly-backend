package ru.kpfu.itis.quailly.egg.repository.jooq

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import ru.kpfu.itis.quailly.egg.repository.account
import ru.kpfu.itis.quailly.egg.repository.merchandise

@SpringBootTest
internal class JooqSwipeRepositoryTest {

    @Autowired
    private lateinit var swipeRepository: JooqSwipeRepository

    @Autowired
    private lateinit var accountRepository: JooqAccountRepository

    @Autowired
    private lateinit var merchandiseRepository: JooqMerchandiseRepository

    @Test
    fun `test find back swipes`() {
        val emil = accountRepository.create(account("emil"))
        val baton = accountRepository.create(account("baton"))
        val emilMerchandises = ""
        merchandiseRepository.create(merchandise("MAC-13", baton.id!!))
        merchandiseRepository.create(merchandise("qiwi", baton.id!!))
        merchandiseRepository.create(merchandise("kolonka", baton.id!!))
        merchandiseRepository.create(merchandise("", baton.id!!))
    }

}