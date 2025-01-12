package com.develcode.payment_gateway.producer;

import com.develcode.payment_gateway.model.PaymentMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    private final RabbitTemplate rabbitTemplate;

    // Injeção do RabbitTemplate para enviar mensagens
    public PaymentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    // Método que envia a mensagem para a fila 'payment'
    public void sendPaymentMessage(PaymentMessage paymentMessage) {
        String routingKey = "payment"; // A fila para onde a mensagem será enviada
        rabbitTemplate.convertAndSend(routingKey, paymentMessage); // Envia a mensagem
    }
}
