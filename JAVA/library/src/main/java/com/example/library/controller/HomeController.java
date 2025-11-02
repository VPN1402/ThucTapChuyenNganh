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
        return "thuvien";
    }
    @GetMapping("/sachcuatoi")
    public String sachcuatoi() {
        return "sachcuatoi";
    }
    @GetMapping("/dangnhap")
    public String dangnhap() {
        return "dangnhap";
    }
    @GetMapping("/dangky")
    public String dangky() {
        return "dangky";
    }
}