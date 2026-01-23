package com.example.notification.consumer;

import com.example.events.PaymentFailedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentFailedConsumer {

    @KafkaListener(
            topics = "payment-failed",
            containerFactory = "paymentFailedEventConcurrentKafkaListenerContainerFactory",
            groupId = "notification-payment-failed"
    )
    public void handlePaymentFailed(PaymentFailedEvent event) {
        log.info("Notification service received PaymentFailedEvent: {}", event);
    }

}
