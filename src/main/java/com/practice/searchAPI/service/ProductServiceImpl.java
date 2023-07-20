package com.practice.searchAPI.service;

import com.practice.searchAPI.model.Product;
import com.practice.searchAPI.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> searchProduct(String query) {
        List<Product> products = productRepository.searchProduct(query);
        return products;
    }

    @Override
    public Product createProduct(Product product) {
       return productRepository.save(product);
    }
}
