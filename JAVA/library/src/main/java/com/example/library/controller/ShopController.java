package com.example.library.controller;

import com.example.library.entity.Category;
import com.example.library.entity.Product;
import com.example.library.service.CategoryService;
import com.example.library.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shop") // Tất cả link trong này sẽ bắt đầu bằng /shop (Ví dụ: localhost:8080/shop)
public class ShopController {

    private final ProductService productService;
    private final CategoryService categoryService;

    // Constructor Injection (Spring tự động tiêm Service vào đây)
    public ShopController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // 1. Trang danh sách sản phẩm (Trang chủ của Shop)
    // URL: http://localhost:8080/shop
    @GetMapping("") // Sửa lại thành chuỗi rỗng
    public String shopIndex(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Product> products = productService.findAll();


        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "home/shop";
    }

    // 2. Lọc sản phẩm theo Category
    // URL: http://localhost:8080/shop/category/1
    @GetMapping("/category/{id}")
    public String filterByCategory(@PathVariable("id") int id, Model model) {
        System.out.println("--- Đang lọc sách cho Category ID: " + id); // Log kiểm tra

        // 1. Tìm tất cả sách thuộc ID thể loại này
        List<Product> products = productService.findByCategoryId(id);

        // 2. Tìm tên thể loại
        Category category = categoryService.findById(id);
        String catName = (category != null) ? category.getName() : "Không xác định";

        // 3. Gửi dữ liệu sang trang
        model.addAttribute("products", products);
        model.addAttribute("catName", catName);

        System.out.println("--- Số lượng sách tìm thấy: " + (products != null ? products.size() : 0));

        // Trả về file thuvien.html trong thư mục home
        return "home/shop";
    }
    @GetMapping("/theloai")
    public String showAllCategories(Model model) {
        // Lấy tất cả thể loại từ DB
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        // Trả về file HTML mới chỉ hiện các ô Thể loại
        return "home/theloai";
    }
    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable("id") int id, Model model) {
        // Sử dụng findById đã có trong ProductService của bạn
        Product product = productService.findById(id);
        model.addAttribute("p", product);
        return "home/chitiet";
    }
}