package com.practice.transactional.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Payment_info")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String cardHoldername;
    private String cardNumber;
    private int expiryYear;
    private Long orderId;

}
