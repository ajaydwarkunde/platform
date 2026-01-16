package com.example.notification.consumer;

import com.example.events.OrderCompletedEvent;
import com.example.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderCompletedConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "order-completed", groupId = "notification-service")
    public void consume(OrderCompletedEvent event) {
        notificationService.sendNotification(event);
    }
}
