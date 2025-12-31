package com.example.library.dao;

import com.example.library.entity.Contact;

import java.util.List;

public interface ContactDAO {
    void save(Contact contact);
    List<Contact> findAll();
    void deleteById(int id);
}
