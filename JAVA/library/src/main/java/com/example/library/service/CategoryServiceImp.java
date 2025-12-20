package com.example.library.service;

import com.example.library.dao.CategoryDAO;
import com.example.library.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    private CategoryDAO categoryDAO;
    @Autowired
    public CategoryServiceImp(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Category> findAll() {

        return categoryDAO.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDAO.findById(id);
    }
    @Transactional
    @Override
    public Category save(Category category) {
        return categoryDAO.save(category);
    }


    @Transactional
    @Override
    public void deleteById(int id) {
        categoryDAO.deleteById(id);
    }
    @Override
    public long countAllCategories() {
        return categoryDAO.findAll().size();
    }
}