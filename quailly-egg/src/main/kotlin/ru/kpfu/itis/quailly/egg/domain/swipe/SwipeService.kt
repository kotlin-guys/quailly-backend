package ru.kpfu.itis.quailly.egg.domain.swipe

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import ru.kpfu.itis.quailly.egg.domain.model.Exchange
import ru.kpfu.itis.quailly.egg.domain.model.ExchangeStatus
import ru.kpfu.itis.quailly.egg.domain.model.Swipe
import ru.kpfu.itis.quailly.egg.domain.model.SwipeDirection
import ru.kpfu.itis.quailly.egg.repository.api.ExchangeRepository
import ru.kpfu.itis.quailly.egg.repository.api.SwipeRepository
import java.time.OffsetDateTime

@Service
class SwipeService(
    private val swipeRepository: SwipeRepository,
    private val exchangeRepository: ExchangeRepository
) {

    fun swipe(request: SwipeRequest, accountId: Long): Mono<SwipeResult> {
        swipeRepository.create(
            Swipe(
                accountId = accountId,
                merchandiseId = request.merchandiseId,
                direction = request.direction
            )
        )
        if (request.direction == SwipeDirection.RIGHT) {
            val swipeBack = swipeRepository.findBackSwipeForMerchandise(request.merchandiseId, accountId)
            if (swipeBack != null) {
                exchangeRepository.create(
                    Exchange(
                        publicationDateTime = OffsetDateTime.now(),
                        initiatorId = accountId,
                        exchangeStatus = ExchangeStatus.COMMUNICATION_PENDING,
                        firstMerchandiseId = swipeBack.merchandiseId,
                        secondMerchandiseId = request.merchandiseId,
                        firstAccepted = false,
                        secondAccepted = false
                    )
                )
            }
        }
        return Mono.just(SwipeResult.Success)
    }
}