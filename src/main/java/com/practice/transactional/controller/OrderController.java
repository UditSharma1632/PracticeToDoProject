package com.practice.transactional.controller;

import com.practice.transactional.dto.OrderRequest;
import com.practice.transactional.dto.OrderResponse;
import com.practice.transactional.service.OrderService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/post")
    public ResponseEntity<OrderResponse> responseOrder(@RequestBody OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.responseOrder(orderRequest));
    }
}
