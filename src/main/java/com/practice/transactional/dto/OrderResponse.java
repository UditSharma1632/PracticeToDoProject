package com.practice.transactional.dto;

import lombok.Data;

@Data
public class OrderResponse {
    private String trackingNumber;
    private String message;
}
