package com.example.events;

import java.time.LocalDateTime;

public class OrderCompletedEvent implements DomainEvent {

    private String orderId;
    private LocalDateTime occurredAt;

    // Required by Jackson
    public OrderCompletedEvent() {
    }

    public OrderCompletedEvent(String orderId) {
        this.orderId = orderId;
        this.occurredAt = LocalDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
