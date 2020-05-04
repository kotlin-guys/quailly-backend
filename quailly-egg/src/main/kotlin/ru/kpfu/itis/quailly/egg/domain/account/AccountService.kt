package ru.kpfu.itis.quailly.egg.domain.account

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
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

    private fun verifyAccount(accountCreationData: AccountCreationData): Account? {
        val account = accountRepository.findByEmail(accountCreationData.email)
        return account ?: run {
            val now = ZonedDateTime.now(ZoneId.of(zoneId))
            accountRepository.create(
                Account(
                    email = accountCreationData.email,
                    emailVerified = accountCreationData.emailVerified,
                    firstName = accountCreationData.name,
                    familyName = accountCreationData.familyName,
                    givenName = accountCreationData.givenName,
                    locale = accountCreationData.locale,
                    pictureUrl = accountCreationData.pictureUrl,
                    registrationDatetime = now.toOffsetDateTime(),
                    lastVisit = now.toOffsetDateTime()
                )
            )
        }
    }

    fun signIn(accountCreationData: AccountCreationData): Mono<SignInStatus> {
        verifyAccount(accountCreationData)
        return Mono.just(SignInStatus.Success(tokenGenerator.generate()))
    }
}

sealed class SignInStatus {
    data class Success(val token: String) : SignInStatus()
    data class Fail(val reason: String) : SignInStatus()
}
