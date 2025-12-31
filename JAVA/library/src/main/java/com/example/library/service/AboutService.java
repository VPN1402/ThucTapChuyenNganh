package com.example.library.service;

import com.example.library.entity.About;
import java.util.List;

public interface AboutService {
    List<About> findAll();
    About findById(int id);
    void update(About about);
}