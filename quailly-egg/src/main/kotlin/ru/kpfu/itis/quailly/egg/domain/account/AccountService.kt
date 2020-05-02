package ru.kpfu.itis.quailly.egg.domain.account

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.kpfu.itis.quailly.egg.domain.model.Account
import ru.kpfu.itis.quailly.egg.generator.token.TokenGenerator
import ru.kpfu.itis.quailly.egg.repository.AccountRepository
import java.time.ZoneId
import java.time.ZonedDateTime

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val tokenGenerator: TokenGenerator
) {

    @Value("\${quailly.server.zone-id}")
    private lateinit var zoneId: String

    private fun verifyAccount(accountVerificationData: AccountVerificationData): Boolean {
        val accountEmail = accountVerificationData.email
        val account = accountRepository.findByEmail(accountEmail)

        if (account == null) {
            accountRepository.create(
                Account(
                    email = accountVerificationData.email,
                    emailVerified = accountVerificationData.emailVerified,
                    firstName = accountVerificationData.name,
                    familyName = accountVerificationData.familyName,
                    givenName = accountVerificationData.givenName,
                    locale = accountVerificationData.locale,
                    pictureUrl = accountVerificationData.pictureUrl,
                    registrationDateTime = ZonedDateTime.now(ZoneId.of(zoneId))
                )
            )
        }
        return account == null
    }

    fun signIn(accountVerificationData: AccountVerificationData): SignInStatus {
        verifyAccount(accountVerificationData)
        return SignInStatus.Success(tokenGenerator.generate())
    }
}

sealed class SignInStatus {
    data class Success(val token: String) : SignInStatus()
    data class Fail(val reason: String) : SignInStatus()
}
