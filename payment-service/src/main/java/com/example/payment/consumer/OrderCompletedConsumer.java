package com.example.payment.consumer;

import com.example.events.OrderCompletedEvent;
import com.example.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCompletedConsumer {

    private final PaymentService paymentService;

    @KafkaListener(topics = "order-completed", groupId = "payment-service")
    public void consume(OrderCompletedEvent event) {
        paymentService.initiatePayment(event);
    }
}
