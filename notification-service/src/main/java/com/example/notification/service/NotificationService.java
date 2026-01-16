package com.example.notification.service;

import com.example.events.OrderCompletedEvent;
import com.example.notification.model.Notification;
import com.example.notification.model.NotificationType;
import com.example.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendNotification(OrderCompletedEvent event) {

        Notification notification = new Notification();
        notification.setOrderId(event.getOrderId());
        notification.setType(NotificationType.EMAIL);
        notification.setSentAt(LocalDateTime.now());

        notificationRepository.save(notification);

        System.out.println("Notification sent for order: " + event.getOrderId());
    }
}
