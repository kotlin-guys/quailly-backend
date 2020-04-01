package ru.kpfu.itis.quailly.egg.web

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.relaxedResponseFields
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import ru.kpfu.itis.quailly.egg.config.AccountRepresentationProcessor
import ru.kpfu.itis.quailly.egg.domain.model.Account
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise
import ru.kpfu.itis.quailly.egg.repository.AccountRepository
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*


@WebMvcTest()
@ContextConfiguration(classes = [AccountRestTest.TestConfig::class])
@AutoConfigureMockMvc
@AutoConfigureRestDocs(outputDir = "/asciidoc/snippets/account")
internal class AccountRestTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Mock
    private lateinit var accountRepository: AccountRepository

    @BeforeEach
    fun setUp() {
        `when`(accountRepository.findAll()).thenReturn(listOf())
    }

    @Test
    fun `get accounts`() {
        mockMvc.perform(get("/accounts"))
            .andDo(org.springframework.test.web.servlet.result.MockMvcResultHandlers.print())
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.email").value(account().email))
            .andDo(
                document(
                    "account", relaxedResponseFields(
                        fieldWithPath("email").description("Email")
                    )
                )
            )
    }

    private fun account(
        username: String = "Azat",
        email: String = "azat@azat",
        phoneNumber: String = "+228",
        registrationDateTime: ZonedDateTime = ZonedDateTime.now(ZoneId.of("Europe/Moscow")),
        merchandises: List<Merchandise> = listOf(),
        password: String = UUID.randomUUID().toString()
    ) = Account(
        username = username,
        email = email,
        password = password,
        phoneNumber = phoneNumber,
        registrationDateTime = registrationDateTime,
        merchandises = merchandises
    )

    @TestConfiguration
    open class TestConfig {

        @Bean
        open fun accountRepository(): AccountRepository = mock(AccountRepository::class.java)

        @Bean
        open fun accountRepresentationProcessor()  = AccountRepresentationProcessor()
    }
}