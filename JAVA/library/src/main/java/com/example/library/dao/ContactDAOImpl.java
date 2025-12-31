package com.example.library.dao;

import com.example.library.entity.Contact;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class ContactDAOImpl implements ContactDAO {

    private EntityManager entityManager;

    @Autowired
    public ContactDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Contact contact) {
        entityManager.persist(contact);
    }

    @Override
    public List<Contact> findAll() {
        TypedQuery<Contact> query = entityManager.createQuery("from Contact order by createdAt desc", Contact.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Contact contact = entityManager.find(Contact.class, id);
        if (contact != null) {
            entityManager.remove(contact);
        }
    }
}