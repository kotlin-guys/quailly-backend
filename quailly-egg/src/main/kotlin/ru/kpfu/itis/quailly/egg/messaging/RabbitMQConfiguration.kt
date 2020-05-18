package ru.kpfu.itis.quailly.egg.messaging

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class RabbitMQConfiguration(
    @Value("\${spring.rabbitmq.template.queue:swipe}")
    private val queueName: String,
    @Value("\${spring.rabbitmq.template.exchange}")
    private val exchange: String,
    @Value("\${spring.rabbitmq.template.routing-key}")
    private val routingKey: String
) {

    @Bean
    open fun queue(): Queue = Queue(queueName, false)

    @Bean
    open fun exchange(): DirectExchange = DirectExchange(exchange)

    @Bean
    open fun binding(queue: Queue, exchange: DirectExchange): Binding =
        BindingBuilder.bind(queue)
            .to(exchange)
            .with(routingKey)

    @Bean
    open fun jsonMessageConverter(objectMapper: ObjectMapper): MessageConverter =
        Jackson2JsonMessageConverter(objectMapper)

    @Bean
    open fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        messageConverter: MessageConverter
    ): AmqpTemplate = RabbitTemplate(connectionFactory).apply {
        this.messageConverter = messageConverter
    }
}