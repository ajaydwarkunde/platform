package com.example.events;

import java.time.LocalDateTime;

public class OrderCreatedEvent implements DomainEvent {

    private String orderId;
    private String customerId;
    private LocalDateTime occurredAt;

    // Required by Jackson
    public OrderCreatedEvent() {
    }

    public OrderCreatedEvent(String orderId, String customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.occurredAt = LocalDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
