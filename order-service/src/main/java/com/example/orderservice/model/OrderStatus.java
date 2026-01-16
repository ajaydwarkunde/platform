package com.example.orderservice.model;

import java.util.EnumSet;
import java.util.Set;

public enum OrderStatus {
    PENDING,
    COMPLETED,
    CANCELLED;

    private Set<OrderStatus> allowedNextStatuses;

    static {
        PENDING.allowedNextStatuses = EnumSet.of(COMPLETED, CANCELLED);
        COMPLETED.allowedNextStatuses = EnumSet.noneOf(OrderStatus.class);
        CANCELLED.allowedNextStatuses = EnumSet.noneOf(OrderStatus.class);
    }

    public boolean canTransitionTo(OrderStatus nextStatus) {
        return allowedNextStatuses.contains(nextStatus);
    }
}
