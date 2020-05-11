package ru.kpfu.itis.quailly.egg

import org.springframework.test.web.reactive.server.WebTestClient
import ru.kpfu.itis.quailly.egg.domain.account.AccountCreationData
import ru.kpfu.itis.quailly.egg.domain.account.SignInStatus
import ru.kpfu.itis.quailly.egg.domain.merchandise.MerchandiseCreationRequest
import ru.kpfu.itis.quailly.egg.domain.model.Merchandise

fun WebTestClient.retrieveToken(
    data: AccountCreationData
): SignInStatus.Success = this.post()
    .uri("/accounts")
    .bodyValue(data)
    .exchange()
    .expectStatus().isOk
    .expectBody(SignInStatus.Success::class.java)
    .returnResult().responseBody!!

fun WebTestClient.createMerchandise(
    data: MerchandiseCreationRequest,
    token: String
): Merchandise = this.post()
    .uri("/merchandises")
    .header("Authorization", token)
    .bodyValue(data)
    .exchange()
    .expectStatus().isOk
    .returnResult(Merchandise::class.java)
    .responseBody.blockFirst()!!
