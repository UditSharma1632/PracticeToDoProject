package com.practice.transactional.service;

import com.practice.transactional.dto.OrderRequest;
import com.practice.transactional.dto.OrderResponse;

public interface OrderService {
    OrderResponse responseOrder(OrderRequest orderRequest);
}
