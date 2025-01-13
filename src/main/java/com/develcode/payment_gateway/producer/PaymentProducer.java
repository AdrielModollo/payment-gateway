package com.develcode.payment_gateway.producer;

import com.develcode.payment_gateway.model.PaymentMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private final RabbitTemplate rabbitTemplate;


    public PaymentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPaymentMessage(PaymentMessage paymentMessage) {
        String routingKey = "payment";
        rabbitTemplate.convertAndSend(routingKey, paymentMessage);
    }
}
