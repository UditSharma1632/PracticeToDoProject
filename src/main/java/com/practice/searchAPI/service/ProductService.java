package com.practice.searchAPI.service;

import com.practice.searchAPI.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> searchProduct(String query);

    Product createProduct(Product product);
}
