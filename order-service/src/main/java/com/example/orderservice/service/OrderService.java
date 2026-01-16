package com.example.orderservice.service;

import com.example.orderservice.dto.CreateOrderRequest;
import com.example.orderservice.events.KafkaEventPublisher;
import com.example.orderservice.events.OrderCancelledEvent;
import com.example.orderservice.events.OrderCompletedEvent;
import com.example.orderservice.events.OrderCreatedEvent;
import com.example.orderservice.model.Order;
import com.example.orderservice.model.OrderStatus;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaEventPublisher kafkaEventPublisher;

    public Order createOrder(CreateOrderRequest request) {

        Order order = Order.builder()
                .customerId(request.getCustomerId())
                .productIds(request.getProductIds())
                .totalAmount(request.getTotalAmount())
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        kafkaEventPublisher.publish(
                "order-created",
                new OrderCreatedEvent(
                        savedOrder.getId(),
                        savedOrder.getCustomerId()
                )
        );

        return savedOrder;

    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public Order updateStatus(String orderId, OrderStatus newStatus) {

        Order order = getOrderById(orderId);
        OrderStatus currentStatus = order.getStatus();

        if (!currentStatus.canTransitionTo(newStatus)) {
            throw new IllegalStateException(
                    "Invalid transition from " + currentStatus + " to " + newStatus
            );
        }

        order.setStatus(newStatus);
        Order savedOrder = orderRepository.save(order);
        if (savedOrder.getStatus() == OrderStatus.COMPLETED) {
            kafkaEventPublisher.publish(
                    "order-completed",
                    new OrderCompletedEvent(savedOrder.getId())
            );
        } else if (savedOrder.getStatus() == OrderStatus.CANCELLED) {
            kafkaEventPublisher.publish(
                    "order-cancelled",
                    new OrderCancelledEvent(savedOrder.getId(), "Cancelled by user")
            );
        }
        return savedOrder;
    }
}
