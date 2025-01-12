package com.develcode.payment_gateway;

import com.develcode.payment_gateway.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RabbitMQConfigTest {

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    @Test
    void testMessageConverter() {
        Jackson2JsonMessageConverter converter = rabbitMQConfig.messageConverter();
        assertThat(converter).isNotNull();
    }

    @Test
    void testCheckoutQueue() {
        Queue queue = rabbitMQConfig.checkoutQueue();
        assertThat(queue.getName()).isEqualTo("checkout");
        assertThat(queue.isDurable()).isTrue();
    }

    @Test
    void testPaymentQueue() {
        Queue queue = rabbitMQConfig.paymentQueue();
        assertThat(queue.getName()).isEqualTo("payment");
        assertThat(queue.isDurable()).isTrue();
    }
}
