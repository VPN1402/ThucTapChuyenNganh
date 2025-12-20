package com.example.library.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")//khớp trong dữ liệu wamp
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "images")
    private String image;
    @Column(name = "status")
    private int status;
    @OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }
    public void addProduct(Product tempProduct) {
        if(products==null){
            products=new ArrayList<>();
        }
        products.add(tempProduct);
        tempProduct.setCategory(this);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Category() {
    }

    public Category(String name, String image, int status) {
        this.name = name;
        this.image = image;
        this.status = status;
        // neu khong tao doi so cho id thi trong database phải để AUTO_INCREMENT
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
