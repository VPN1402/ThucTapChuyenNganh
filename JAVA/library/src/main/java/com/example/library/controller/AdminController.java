package com.example.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/student-list")
    public String studentList(){
        return "admin/student";
    }
    @GetMapping("/category")
    public String categoryList(){
        return "admin/category/category-list";
    }
    @GetMapping("/products")
    public String productsList(){
        return "admin/products/products-list";
    }


}
