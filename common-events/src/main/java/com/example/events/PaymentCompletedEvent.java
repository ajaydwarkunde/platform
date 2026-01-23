package com.example.events;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentCompletedEvent {
    private String orderId;
    private String paymentId;
    private double amount;
}
