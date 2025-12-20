package com.example.library.dao;

import com.example.library.entity.Category;
import org.springframework.stereotype.Repository;


public interface AppDAO {
    void save(Category theCategory);
    Category findCategoryById(int theId);
    void deleteCategoryById(int theId);

}
