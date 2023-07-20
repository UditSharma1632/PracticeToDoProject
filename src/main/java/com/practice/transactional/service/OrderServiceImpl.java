package com.practice.transactional.service;

import com.practice.transactional.Exception.PaymentException;
import com.practice.transactional.dto.OrderRequest;
import com.practice.transactional.dto.OrderResponse;
import com.practice.transactional.model.Order;
import com.practice.transactional.model.Payment;
import com.practice.transactional.repository.OrderRepository;
import com.practice.transactional.repository.PaymentRespository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService{
    private OrderRepository orderRepository;
    private PaymentRespository paymentRespository;

    public OrderServiceImpl(OrderRepository orderRepository, PaymentRespository paymentRespository) {
        this.orderRepository = orderRepository;
        this.paymentRespository = paymentRespository;
    }

    @Override
    @Transactional
    public OrderResponse responseOrder(OrderRequest orderRequest) {
        Order order = orderRequest.getOrder();
        order.setTrackingNumber(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = orderRequest.getPayment();

        if (!payment.getType().equals("UPI")){
            throw new PaymentException("Invalid Payment Method");
        }
        payment.setOrderId(order.getId());
        paymentRespository.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setTrackingNumber(order.getTrackingNumber());
        orderResponse.setMessage("Success");
        return orderResponse;
    }
}
