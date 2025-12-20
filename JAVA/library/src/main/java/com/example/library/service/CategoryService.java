package com.example.library.service;

import com.example.library.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
    Category save(Category category);
    void deleteById(int id);

    long countAllCategories();
}