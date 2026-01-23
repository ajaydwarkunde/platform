package com.example.notification.consumer;

import com.example.events.PaymentCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentCompletedConsumer {

    @KafkaListener(
            topics = "payment-completed",
            containerFactory = "paymentCompletedEventConcurrentKafkaListenerContainerFactory",
            groupId = "notification-payment-completed"
    )
    public void handlePaymentCompleted(PaymentCompletedEvent event) {
        log.info("Notification service received PaymentCompletedEvent: {}", event);
    }

}
