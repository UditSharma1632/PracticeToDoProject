package com.practice.transactional.repository;

import com.practice.transactional.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
        }
