package com.example.events;

import java.time.LocalDateTime;

public class OrderCancelledEvent implements DomainEvent {

    private String orderId;
    private String reason;
    private LocalDateTime occurredAt;

    // Required by Jackson
    public OrderCancelledEvent() {
    }

    public OrderCancelledEvent(String orderId, String reason) {
        this.orderId = orderId;
        this.reason = reason;
        this.occurredAt = LocalDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
