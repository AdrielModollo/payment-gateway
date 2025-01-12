package com.develcode.payment_gateway.model;

public class PaymentMessage {

    private Long orderId;
    private String status;

    // Getters e Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentMessage{" +
                "orderId=" + orderId +
                ", status='" + status + '\'' +
                '}';
    }
}
