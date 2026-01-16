package com.example.payment.service;

import com.example.events.OrderCompletedEvent;
import com.example.payment.model.Payment;
import com.example.payment.model.PaymentStatus;
import com.example.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public void initiatePayment(OrderCompletedEvent event) {

        Payment payment = new Payment();
        payment.setOrderId(event.getOrderId());
        payment.setStatus(PaymentStatus.INITIATED);
        payment.setCreatedAt(LocalDateTime.now());

        paymentRepository.save(payment);

        System.out.println("Payment initiated for order: " + event.getOrderId());
    }
}
