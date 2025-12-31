package com.example.library.dao;

import com.example.library.entity.About;
import java.util.List;

public interface AboutDAO {
    List<About> findAll();
    About findById(int id);
    void update(About about);
}