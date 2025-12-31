package com.example.library.controller;

import com.example.library.dao.ContactDAO;
import com.example.library.entity.About;
import com.example.library.entity.Product;
import com.example.library.service.AboutService;
import com.example.library.service.CategoryService;
import com.example.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Đã sửa import ở đây
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

;
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired // Đảm bảo có dòng này ở đây
    private JdbcTemplate jdbcTemplate;
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

    @Autowired
    private ContactDAO contactDAO; // Sử dụng DAO bạn đã viết

    @GetMapping("/contacts")
    public String listContacts(Model model) {
        model.addAttribute("contacts", contactDAO.findAll());
        return "admin/contact-list"; // Bỏ dấu / ở đầu
    }

    @GetMapping("/contacts/delete/{id}")
    public String delete(@PathVariable int id) {
        contactDAO.deleteById(id);
        return "redirect:/admin/contacts"; // Thêm dấu / ở đầu
    }

    @Autowired
    private AboutService aboutService;

    // Trang hiện danh sách
    @GetMapping("/about")
    public String listAbout(Model model) {
        model.addAttribute("aboutList", aboutService.findAll());
        return "admin/about-list";
    }

    // Trang hiện form sửa (nhận ID)
    @GetMapping("/about/edit/{id}")
    public String editAbout(@PathVariable int id, Model model) {
        model.addAttribute("about", aboutService.findById(id));
        return "admin/update-about";
    }

    // Xử lý lưu
    @PostMapping("/about/update")
    public String updateAbout(@ModelAttribute("about") About about, RedirectAttributes ra) {
        aboutService.update(about);
        ra.addFlashAttribute("message", "Cập nhật nội dung thành công!");
        return "redirect:/admin/about";
    }





}