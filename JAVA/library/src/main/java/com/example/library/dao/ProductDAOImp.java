package com.example.library.dao;

import com.example.library.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImp implements ProductDAO {
    private EntityManager em;
    public ProductDAOImp(EntityManager em) {
        this.em = em;
    }
    @Override
    public List<Product> findAll() {
        TypedQuery<Product>query=em.createQuery("from Product",
                Product.class);
        return  query.getResultList();
    }

    @Override
    public Product findById(int id) {
        return em.find(Product.class,id);
    }
    @Transactional
    @Override
    public Product save(Product product) {
       Product save=em.merge(product);
       return save;
    }
    @Transactional
    @Override
    public void deleteById(int id) {
        Product product=em.find(Product.class,id);
        em.remove(product);
    }
    @Override
    public List<Product> findByCategoryId(int categoryId) {
        // Viết câu lệnh HQL/JPQL để lấy sản phẩm thuộc category cụ thể
        TypedQuery<Product> query = em.createQuery(
                "from Product p where p.category.id = :theId", Product.class);
        query.setParameter("theId", categoryId);
        return query.getResultList();
    }

}
