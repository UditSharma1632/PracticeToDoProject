package com.practice.transactional.repository;

import com.practice.transactional.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRespository extends JpaRepository<Payment, Long> {
}
