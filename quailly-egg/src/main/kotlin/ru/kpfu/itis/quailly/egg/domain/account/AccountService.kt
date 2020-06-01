package ru.kpfu.itis.quailly.egg.domain.account

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers
import ru.kpfu.itis.quailly.egg.domain.model.Account
import ru.kpfu.itis.quailly.egg.generator.token.TokenGenerator
import ru.kpfu.itis.quailly.egg.repository.api.AccountRepository
import java.time.OffsetDateTime
import java.time.ZoneId

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val tokenGenerator: TokenGenerator
) {

    @Value("\${quailly.server.zone-id}")
    private lateinit var zoneId: String

    private fun verifyAccount(accountCreationData: AccountCreationData): Mono<Account?> {
        return Mono
            .justOrEmpty(accountRepository.findByEmail(accountCreationData.email))
            .switchIfEmpty(
                Mono.defer {
                    Mono.just(
                        accountRepository.create(
                            Account(
                                email = accountCreationData.email,
                                emailVerified = accountCreationData.emailVerified,
                                firstName = accountCreationData.name,
                                familyName = accountCreationData.familyName,
                                givenName = accountCreationData.givenName,
                                locale = accountCreationData.locale,
                                pictureUrl = accountCreationData.pictureUrl,
                                registrationDatetime = OffsetDateTime.now(ZoneId.of(zoneId)),
                                lastVisit = OffsetDateTime.now(ZoneId.of(zoneId)),
                                token = tokenGenerator.generate()
                            )
                        )
                    )
                }
            )
    }

    fun signIn(accountCreationData: AccountCreationData): Mono<SignInStatus> {
        return verifyAccount(accountCreationData)
            .map {
                when (val token = it!!.token) {
                    null -> SignInStatus.Fail("Token is invalid.")
                    else -> SignInStatus.Success(token)
                }
            }
            .subscribeOn(Schedulers.parallel())
    }
}

sealed class SignInStatus {
    data class Success(val token: String) : SignInStatus()
    data class Fail(val reason: String) : SignInStatus()
}
