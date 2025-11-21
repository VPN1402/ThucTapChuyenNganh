package com.example.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }
    @GetMapping("/thuvien")
    public String thuvien() {
        return "home/thuvien";
    }
    @GetMapping("/sachcuatoi")
    public String sachcuatoi() {
        return "home/sachcuatoi";
    }
    @GetMapping("/dangnhap")
    public String dangnhap() {
        return "home/dangnhap";
    }
    @GetMapping("/dangky")
    public String dangky() {
        return "home/dangky";
    }
    @GetMapping("/lienhe")
    public String lienhe() {
        return "home/lienhe";
    }
    @GetMapping("/taikhoan")
    public String taikhoan() {
        return "home/taikhoan";
    }
    @GetMapping("/admin")

    public String admin() {
        return "admin";
    }

}