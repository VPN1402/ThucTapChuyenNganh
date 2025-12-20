package com.example.library.service;

import com.example.library.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product findById(int id);
    Product save(Product product);
    void deleteById(int id);
    List<Product> findByCategoryId(int categoryId);
    long countAllProducts();

}
