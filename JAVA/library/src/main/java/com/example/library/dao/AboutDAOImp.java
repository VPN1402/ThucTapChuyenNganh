package com.example.library.dao;

import com.example.library.entity.About;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class AboutDAOImp implements AboutDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<About> findAll() {
        String sql = "SELECT * FROM about_content";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(About.class));
    }

    @Override
    public About findById(int id) {
        String sql = "SELECT * FROM about_content WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(About.class), id);
    }

    @Override
    public void update(About about) {
        String sql = "UPDATE about_content SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, about.getTitle(), about.getContent(), about.getId());
    }
}