package com.develcode.payment_gateway.listener;

import com.develcode.payment_gateway.model.PaymentMessage;
import com.develcode.payment_gateway.producer.PaymentProducer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PaymentListener {

    private static final Logger logger = LoggerFactory.getLogger(PaymentListener.class);
    private final PaymentProducer paymentProducer;

    // Injeção do PaymentProducer para enviar mensagens de volta para a fila 'payment'
    public PaymentListener(PaymentProducer paymentProducer) {
        this.paymentProducer = paymentProducer;
    }

    // Método que consome as mensagens da fila 'checkout'
    @RabbitListener(queues = "checkout")
    public void processPayment(PaymentMessage paymentMessage) {
        logger.info("Mensagem recebida da fila 'checkout': " + paymentMessage);

        // Verifica o status da mensagem
        if ("payment_fail".equals(paymentMessage.getStatus())) {
            // Atualiza o status e envia de volta para a fila 'payment'
            paymentMessage.setStatus("payment_fail");
            paymentProducer.sendPaymentMessage(paymentMessage);
            logger.info("Pagamento falhou para a ordem " + paymentMessage.getOrderId() + ", mensagem reenviada para a fila 'payment'.");
        } else {
            // Se o pagamento foi bem-sucedido, processa normalmente
            paymentMessage.setStatus("payment_success");
            paymentProducer.sendPaymentMessage(paymentMessage);
            logger.info("Pagamento autorizado para a ordem " + paymentMessage.getOrderId() + ", mensagem enviada para a fila 'payment'.");
        }
    }
}
