package com.example.payment.consumer;

import com.example.events.OrderCreatedEvent;
import com.example.events.PaymentCompletedEvent;
import com.example.events.PaymentFailedEvent;
import com.example.payment.util.PaymentTopics;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCreatedConsumer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(
            topics = "order-created",
            groupId = "payment-service"
    )
    public void handleOrderCreated(OrderCreatedEvent event) {
        log.info("Payment service received OrderCreatedEvent: {}", event);

        boolean paymentSuccess = processPayment(event);

        if (paymentSuccess) {
            PaymentCompletedEvent completedEvent =
                    new PaymentCompletedEvent(
                            event.getOrderId(),
                            UUID.randomUUID().toString(),
                            499.99
                    );

            kafkaTemplate.send(
                    PaymentTopics.PAYMENT_COMPLETED,
                    completedEvent
            );

        } else {
            PaymentFailedEvent failedEvent =
                    new PaymentFailedEvent(
                            event.getOrderId(),
                            "Insufficient balance"
                    );

            kafkaTemplate.send(
                    PaymentTopics.PAYMENT_FAILED,
                    failedEvent
            );
        }
    }

    private boolean processPayment(OrderCreatedEvent event) {
        // simulate payment
        return Math.random() > 0.3;
    }

    @PostConstruct
    public void verifyKafkaProducer() {
        System.out.println(">>> PAYMENT KafkaTemplate = " + kafkaTemplate.toString());
    }


}
