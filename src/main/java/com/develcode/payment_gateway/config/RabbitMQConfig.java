package com.develcode.payment_gateway.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

@Configuration
@EnableRabbit
public class RabbitMQConfig {

    // Bean para o conversor de mensagens (Jackson)
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Fila de checkout
    @Bean
    public Queue checkoutQueue() {
        return new Queue("checkout", true); // persistente
    }

    // Fila de payment
    @Bean
    public Queue paymentQueue() {
        return new Queue("payment", true); // persistente
    }
}