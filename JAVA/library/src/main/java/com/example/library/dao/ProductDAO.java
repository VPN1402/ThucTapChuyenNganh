package com.example.library.dao;

import com.example.library.entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> findAll();
    Product findById(int id);
    Product save(Product product);
    void deleteById(int id);
    List<Product> findByCategoryId(int categoryId);


}
