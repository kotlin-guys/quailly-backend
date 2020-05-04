package ru.kpfu.itis.quailly.egg.domain.account

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.kpfu.itis.quailly.egg.domain.model.Account
import ru.kpfu.itis.quailly.egg.generator.token.TokenGenerator
import ru.kpfu.itis.quailly.egg.repository.api.AccountRepository
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val tokenGenerator: TokenGenerator
) {

    @Value("\${quailly.server.zone-id}")
    private lateinit var zoneId: String

    private fun verifyAccount(accountCreationData: AccountCreationData): Boolean {
        val accountEmail = accountCreationData.email
        val account = accountRepository.findByEmail(accountEmail)

        if (account == null) {
            accountRepository.create(
                Account(
                    email = accountCreationData.email,
                    emailVerified = accountCreationData.emailVerified,
                    firstName = accountCreationData.name,
                    familyName = accountCreationData.familyName,
                    givenName = accountCreationData.givenName,
                    locale = accountCreationData.locale,
                    pictureUrl = accountCreationData.pictureUrl,
                    registrationDateTime = ZonedDateTime.now(ZoneId.of(zoneId))
                )
            )
        }
        return account == null
    }

    fun signIn(accountCreationData: AccountCreationData): SignInStatus {
        verifyAccount(accountCreationData)
        return SignInStatus.Success(tokenGenerator.generate())
    }
}

sealed class SignInStatus {
    data class Success(val token: String) : SignInStatus()
    data class Fail(val reason: String) : SignInStatus()
}
