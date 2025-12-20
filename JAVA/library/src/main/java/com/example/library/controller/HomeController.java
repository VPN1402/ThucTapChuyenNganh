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
    @GetMapping("/dangky")
    public String dangky() {
        return "home/dangky";
    }
    @GetMapping("/lienhe")
    public String lienhe() {
        return "home/lienhe";
    }
    @GetMapping("/chitiet")
    public String chiTiet() {
        return "home/chitet";
    }


}