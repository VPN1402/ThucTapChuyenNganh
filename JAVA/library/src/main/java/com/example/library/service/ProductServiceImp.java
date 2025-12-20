package com.example.library.service;

import com.example.library.dao.ProductDAO;
import com.example.library.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@jakarta.transaction.Transactional // Thêm dòng này để áp dụng cho tất cả các hàm
public class ProductServiceImp implements ProductService {
    private ProductDAO productDAO;
    public ProductServiceImp(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDAO.findById(id);
    }
    @Override
    public Product save(Product product) {
        return productDAO.save(product);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        productDAO.deleteById(id);
    }
    @Override
    public List<Product> findByCategoryId(int categoryId) {
        return productDAO.findByCategoryId(categoryId);
    }
    @Override
    public long countAllProducts() {
        // Sử dụng findAll().size() hoặc viết một hàm đếm riêng trong DAO
        return productDAO.findAll().size();
    }

}
