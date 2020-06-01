package ru.kpfu.itis.quailly.egg.messaging

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeData
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeService

@Component
class SwipeConsumer(private val swipeService: SwipeService) {

    @RabbitListener(queues = ["\${spring.rabbitmq.queue}"])
    fun receivedMessage(swipeData: SwipeData) {
        swipeService.swipe(swipeData).subscribe()
    }
}