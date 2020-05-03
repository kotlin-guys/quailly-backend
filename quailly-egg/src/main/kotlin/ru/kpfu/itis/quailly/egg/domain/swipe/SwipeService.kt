package ru.kpfu.itis.quailly.egg.domain.swipe

import org.springframework.stereotype.Service
import ru.kpfu.itis.quailly.egg.domain.model.SwipeHistory
import ru.kpfu.itis.quailly.egg.repository.SwipeHistoryRepository

@Service
class SwipeService(private val swipeHistoryRepository: SwipeHistoryRepository) {


    fun swipe(request: SwipeRequest, accountId: Long): SwipeResult {
        swipeHistoryRepository.create(
            SwipeHistory(
                accountId = accountId,
                merchandiseId = request.merchandiseId,
                direction = request.direction
            )
        )
        swipeHistoryRepository.getById(request.merchandiseId)
        return SwipeResult.Success
    }
}