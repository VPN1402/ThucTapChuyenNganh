package com.example.library.dao;

import com.example.library.entity.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional; // <--- Dùng cái này của Spring

@Repository
public class UserDAOImp implements UserDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean userExists(String username) {
        String sql = "SELECT count(*) FROM users WHERE username = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count != null && count > 0;
    }

    @Override
    public boolean emailExists(String email) {
        String sql = "SELECT count(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    @Transactional
    public void save(UserDto userDto, String encodedPassword) {
        // Lưu vào bảng users
        jdbcTemplate.update("INSERT INTO users (username, password, enabled, email) VALUES (?, ?, ?, ?)",
                userDto.getUsername(), encodedPassword, 1, userDto.getEmail());

        // Lưu vào bảng authorities
        jdbcTemplate.update("INSERT INTO authorities (username, authority) VALUES (?, ?)",
                userDto.getUsername(), "ROLE_USER");
    }
}