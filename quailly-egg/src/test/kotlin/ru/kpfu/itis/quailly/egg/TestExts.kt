package ru.kpfu.itis.quailly.egg

import org.springframework.test.web.reactive.server.WebTestClient
import ru.kpfu.itis.quailly.egg.domain.account.AccountCreationData
import ru.kpfu.itis.quailly.egg.domain.account.SignInStatus

fun WebTestClient.retrieveToken(
    data: AccountCreationData
): SignInStatus.Success = this.post()
    .uri("/accounts")
    .bodyValue(data)
    .exchange()
    .expectStatus().isOk
    .expectBody(SignInStatus.Success::class.java)
    .returnResult().responseBody!!
