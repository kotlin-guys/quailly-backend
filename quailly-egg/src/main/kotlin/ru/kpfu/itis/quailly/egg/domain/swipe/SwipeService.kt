package ru.kpfu.itis.quailly.egg.domain.swipe

import org.springframework.stereotype.Service
import ru.kpfu.itis.quailly.egg.domain.model.Swipe
import ru.kpfu.itis.quailly.egg.repository.SwipeRepository

@Service
class SwipeService(private val swipeRepository: SwipeRepository) {


    fun swipe(request: SwipeRequest, accountId: Long): SwipeResult {
        swipeRepository.create(
            Swipe(
                accountId = accountId,
                merchandiseId = request.merchandiseId,
                direction = request.direction
            )
        )
        swipeRepository.getById(request.merchandiseId)
        return SwipeResult.Success
    }
}