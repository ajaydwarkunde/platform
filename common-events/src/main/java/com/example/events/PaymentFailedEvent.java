package com.example.events;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaymentFailedEvent {
    private String orderId;
    private String reason;
}
