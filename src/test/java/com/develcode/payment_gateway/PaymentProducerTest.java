package com.develcode.payment_gateway;

import com.develcode.payment_gateway.model.PaymentMessage;
import com.develcode.payment_gateway.producer.PaymentProducer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

public class PaymentProducerTest {

    @Test
    void testSendPaymentMessage() {
        RabbitTemplate rabbitTemplate = mock(RabbitTemplate.class);
        PaymentProducer paymentProducer = new PaymentProducer(rabbitTemplate);

        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setOrderId(123L);
        paymentMessage.setStatus("payment_success");

        paymentProducer.sendPaymentMessage(paymentMessage);

        verify(rabbitTemplate, times(1)).convertAndSend("payment", paymentMessage);
    }
}
