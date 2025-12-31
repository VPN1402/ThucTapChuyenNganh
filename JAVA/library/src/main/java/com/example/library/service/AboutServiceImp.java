package com.example.library.service;

import com.example.library.dao.AboutDAO;
import com.example.library.entity.About;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AboutServiceImp implements AboutService {

    private final AboutDAO aboutDAO;

    @Autowired
    public AboutServiceImp(AboutDAO aboutDAO) {
        this.aboutDAO = aboutDAO;
    }

    @Override
    public List<About> findAll() {
        return aboutDAO.findAll();
    }

    @Override
    public About findById(int id) {
        return aboutDAO.findById(id);
    }

    @Override
    @Transactional
    public void update(About about) {
        // Có thể thêm logic kiểm tra trước khi update ở đây
        aboutDAO.update(about);
    }
}