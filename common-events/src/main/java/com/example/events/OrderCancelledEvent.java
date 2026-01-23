package com.example.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCancelledEvent implements DomainEvent {

    private String orderId;
    private String reason;
    private LocalDateTime occurredAt;

    public OrderCancelledEvent(String id, String cancelledByUser) {
    }

    @Override
    public LocalDateTime getOccurredAt() {
        return occurredAt;
    }
}
