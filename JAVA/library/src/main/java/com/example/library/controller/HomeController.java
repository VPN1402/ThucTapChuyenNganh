package com.example.library.controller;

import com.example.library.entity.About;
import com.example.library.service.AboutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/thuvien")
    public String thuvien() {
        return "shop";
    }
    @GetMapping("/sachcuatoi")
    public String sachcuatoi() {
        return "theloai";
    }
    @GetMapping("/dangnhap")
    public String dangnhap() {
        return "home/dangnhap";
    }
    @GetMapping("/lienhe")
    public String lienhe() {
        return "home/lienhe";
    }
    @GetMapping("/chitiet")
    public String chiTiet() {
        return "home/chitet";
    }

    @Autowired
    private AboutService aboutService; // Tiêm Service vào để lấy dữ liệu

    @GetMapping("/about")
    public String showAboutPage(Model model) {
        // Lấy mục giới thiệu đầu tiên (LIMIT 1) hoặc theo logic của bạn
        // Ở đây tôi lấy cái đầu tiên trong danh sách
        About about = aboutService.findAll().get(0);

        model.addAttribute("about", about);
        return "home/about"; // Trả về file templates/home/about.html
    }



}