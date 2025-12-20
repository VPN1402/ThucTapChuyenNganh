package com.example.library.dao;

import com.example.library.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    Category findById(int id);
    Category save(Category category);
    void deleteById(int id);

}