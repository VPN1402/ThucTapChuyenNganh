package com.example.library.dao;

import com.example.library.entity.Category;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class AppDAOImp implements AppDAO{
    private EntityManager entityManager;

    public AppDAOImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional
    @Override
    public void save(Category theCategory) {
        entityManager.persist(theCategory);
    }

    @Override
    public Category findCategoryById(int theId) {
        return entityManager.find(Category.class,theId);
    }
    @Transactional
    @Override
    public void deleteCategoryById(int theId) {
        Category tempCategory = entityManager.find(Category.class,theId);
        entityManager.remove(tempCategory);
    }
}