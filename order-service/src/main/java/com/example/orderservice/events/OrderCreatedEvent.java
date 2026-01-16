package com.example.orderservice.events;

import lombok.Getter;

import java.time.LocalDateTime;

public class OrderCreatedEvent implements DomainEvent {

    @Getter
    private final String orderId;
    @Getter
    private final String customerId;
    private final LocalDateTime occurredAt = LocalDateTime.now();

    public OrderCreatedEvent(String orderId, String customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
