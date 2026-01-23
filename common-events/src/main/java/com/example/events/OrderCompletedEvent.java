package com.example.events;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderCompletedEvent implements DomainEvent {

    private String orderId;
    private LocalDateTime occurredAt;

    public OrderCompletedEvent(String orderId) {
        this.orderId = orderId;
        this.occurredAt = LocalDateTime.now();
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
