package com.example.payment.consumer;

import com.example.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderCancelledConsumer {

    @KafkaListener(
            topics = "order-cancelled",
            groupId = "payment-service"
    )
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("Payment service received OrderCancelledEvent: {}", event);
    }
}
