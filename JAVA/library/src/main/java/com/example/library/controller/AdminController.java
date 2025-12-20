package com.example.library.controller;

import com.example.library.entity.Product;
import com.example.library.service.CategoryService;
import com.example.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Đã sửa import ở đây
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public AdminController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("") // Trang Dashboard chính (localhost:8080/admin)
    public String adminDashboard(Model model) {
        // Thay đổi ở đây: Dùng findAll().size() thay vì count
        long totalBooks = productService.findAll().size();
        long totalCategories = categoryService.findAll().size();

        List<Product> recentBooks = productService.findAll();

        model.addAttribute("totalBooks", totalBooks);
        model.addAttribute("totalCategories", totalCategories);
        model.addAttribute("recentBooks", recentBooks);

        model.addAttribute("borrowedBooks", 0);
        model.addAttribute("availableBooks", totalBooks);

        return "admin"; // Lưu ý: Nếu file nằm trong thư mục admin thì phải là "admin/admin"
    }

    @GetMapping("/products")
    public String productsList(){
        return "admin/products/products-list";
    }
    @GetMapping("/category")
    public String categoryList(){
        return "admin/category/category-list";
    }

}