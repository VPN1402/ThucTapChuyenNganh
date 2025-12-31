package com.example.library.service;

import com.example.library.dao.UserDAO;
import com.example.library.entity.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Quan trọng

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public String registerNewUser(UserDto userDto) {
        // 1. Kiểm tra trùng Username
        if (userDAO.userExists(userDto.getUsername())) {
            return "Tên đăng nhập đã tồn tại!";
        }

        // 2. Kiểm tra trùng Email
        if (userDAO.emailExists(userDto.getEmail())) {
            return "Email này đã được sử dụng!";
        }

        // 3. Mã hóa mật khẩu và lưu
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDAO.save(userDto, encodedPassword);

        return null;
    }
}