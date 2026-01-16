package com.example.orderservice.events;

import lombok.Getter;

import java.time.LocalDateTime;

public class OrderCompletedEvent implements DomainEvent {

    @Getter
    private final String orderId;
    private final LocalDateTime occurredAt = LocalDateTime.now();

    public OrderCompletedEvent(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
