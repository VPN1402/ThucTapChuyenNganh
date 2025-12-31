package com.example.library.service;

import com.example.library.entity.UserDto;

public interface UserService {
    String registerNewUser(UserDto userDto);
}