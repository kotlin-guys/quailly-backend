package ru.kpfu.itis.quailly.egg.domain.swipe

import org.springframework.stereotype.Service
import ru.kpfu.itis.quailly.egg.domain.model.Exchange
import ru.kpfu.itis.quailly.egg.domain.model.ExchangeStatus
import ru.kpfu.itis.quailly.egg.domain.model.Swipe
import ru.kpfu.itis.quailly.egg.repository.api.ExchangeRepository
import ru.kpfu.itis.quailly.egg.repository.api.SwipeRepository
import java.time.ZonedDateTime

@Service
class SwipeService(
    private val swipeRepository: SwipeRepository,
    private val exchangeRepository: ExchangeRepository
) {

    fun swipe(request: SwipeRequest, accountId: Long): SwipeResult {
        swipeRepository.create(
            Swipe(
                accountId = accountId,
                merchandiseId = request.merchandiseId,
                direction = request.direction
            )
        )
        val swipeBack = swipeRepository.findSwipeForExchange(request.merchandiseId, accountId)
        if (swipeBack != null) {
            exchangeRepository.create(
                Exchange(
                    publicationDateTime = ZonedDateTime.now(),
                    authorId = accountId,
                    exchangeStatus = ExchangeStatus.COMMUNICATION_PENDING,
                    firstMerchandiseId = swipeBack.merchandiseId,
                    secondMerchandiseId = request.merchandiseId
                )
            )
        }
        return SwipeResult.Success
    }
}