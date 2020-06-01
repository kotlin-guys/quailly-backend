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

    fun swipe(swipeData: SwipeData): Mono<SwipeResult> {
        return Mono
            .just(
                swipeRepository.create(
                    Swipe(
                        accountId = swipeData.swiperId,
                        merchandiseId = swipeData.merchandiseId,
                        direction = swipeData.direction
                    )
                )
            )
            .flatMap {
                if (swipeData.direction == SwipeDirection.RIGHT) {
                    Mono.justOrEmpty(
                        swipeRepository
                            .findBackSwipeForMerchandise(swipeData.merchandiseId, swipeData.swiperId)
                            ?.also {
                                exchangeRepository.create(
                                    Exchange(
                                        publicationDateTime = OffsetDateTime.now(),
                                        initiatorId = swipeData.swiperId,
                                        exchangeStatus = ExchangeStatus.COMMUNICATION_PENDING,
                                        firstMerchandiseId = it.merchandiseId,
                                        secondMerchandiseId = swipeData.merchandiseId,
                                        firstAccepted = false,
                                        secondAccepted = false
                                    )
                                )
                            }
                    )
                }
                Mono.empty<Swipe>()
            }
            .map { SwipeResult.Success }
    }
}