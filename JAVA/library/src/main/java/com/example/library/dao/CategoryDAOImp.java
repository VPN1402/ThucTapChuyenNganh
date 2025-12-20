package com.example.library.dao;

import com.example.library.entity.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImp implements CategoryDAO{
    private EntityManager em;

    public CategoryDAOImp(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Category> findAll() {
        TypedQuery<Category> query = em.createQuery("from Category",
                Category.class);
        return query.getResultList();
    }

    @Override
    public Category findById(int id) {
        return em.find(Category.class, id);
    }
    @Transactional
    @Override
    public Category save(Category category) {
        Category save = em.merge(category);
        return save;
    }
    @Transactional
    @Override
    public void deleteById(int id) {
        Category category = em.find(Category.class, id);
        em.remove(category);
    }
}
