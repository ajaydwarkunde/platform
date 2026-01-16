package com.example.orderservice.events;

import lombok.Getter;

import java.time.LocalDateTime;

public class OrderCancelledEvent implements DomainEvent {

    @Getter
    private final String orderId;
    @Getter
    private final String reason;
    private final LocalDateTime occurredAt = LocalDateTime.now();

    public OrderCancelledEvent(String orderId, String reason) {
        this.orderId = orderId;
        this.reason = reason;
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
