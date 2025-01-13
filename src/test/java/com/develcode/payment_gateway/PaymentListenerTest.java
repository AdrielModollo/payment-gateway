package com.develcode.payment_gateway;

import com.develcode.payment_gateway.listener.PaymentListener;
import com.develcode.payment_gateway.model.PaymentMessage;
import com.develcode.payment_gateway.producer.PaymentProducer;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class PaymentListenerTest {

    @Test
    void testProcessPayment_FailStatus() {
        PaymentProducer paymentProducer = mock(PaymentProducer.class);
        PaymentListener paymentListener = new PaymentListener(paymentProducer);

        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setOrderId(123L);
        paymentMessage.setAmount(600.0 );

        paymentListener.processPayment(paymentMessage);

        verify(paymentProducer, times(1)).sendPaymentMessage(paymentMessage);
        assert paymentMessage.getStatus().equals("payment_fail");
    }

    @Test
    void testProcessPayment_SuccessStatus() {
        PaymentProducer paymentProducer = mock(PaymentProducer.class);
        PaymentListener paymentListener = new PaymentListener(paymentProducer);

        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setOrderId(123L);
        paymentMessage.setAmount(100.0);

        paymentListener.processPayment(paymentMessage);

        verify(paymentProducer, times(1)).sendPaymentMessage(paymentMessage);
        assert paymentMessage.getStatus().equals("payment_success");
    }
}
