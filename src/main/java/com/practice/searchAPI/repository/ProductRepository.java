package com.practice.searchAPI.repository;

import com.practice.searchAPI.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "p.name LIKE CONCAT('%',:query, '%')"  +
            "Or p.description LIKE CONCAT('%',:query, '%')")
    List<Product> searchProduct(String query);
}