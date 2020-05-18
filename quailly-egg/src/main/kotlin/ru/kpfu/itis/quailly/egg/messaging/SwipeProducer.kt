package ru.kpfu.itis.quailly.egg.messaging

import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import ru.kpfu.itis.quailly.egg.domain.swipe.SwipeData

@Service
class SwipeProducer(
    private val rabbitTemplate: AmqpTemplate,
    @Value("\${spring.rabbitmq.template.exchange}")
    private val exchange: String,
    @Value("\${spring.rabbitmq.template.routing-key}")
    private val routingKey: String
) {

    fun sendSwipe(swipe: SwipeData) {
        rabbitTemplate.convertAndSend(exchange, routingKey, swipe)
    }
}
