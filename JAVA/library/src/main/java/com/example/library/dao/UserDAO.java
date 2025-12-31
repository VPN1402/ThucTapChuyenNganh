package com.example.library.dao;

import com.example.library.entity.UserDto;

public interface UserDAO {
    boolean userExists(String username);
    boolean emailExists(String email);
    void save(UserDto userDto, String encodedPassword);
}