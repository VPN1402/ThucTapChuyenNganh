package com.example.library.controller;

import com.example.library.dao.ContactDAO;
import com.example.library.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {



    @Autowired
    private ContactDAO contactDAO; // Sử dụng DAO bạn đã viết


    // 1. Hiện trang liên hệ
    @GetMapping("/contact")
    public String showContactPage() {
        return "home/contact";
    }

    // 2. Xử lý khi khách bấm nút Gửi
    @PostMapping("/contact/send")
    public String handleSendContact(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String message,
                                    RedirectAttributes ra) {
        // Tạo đối tượng Contact mới
        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);
        contact.setMessage(message);
        // Lưu vào DB qua DAO
        contactDAO.save(contact);

        // Thông báo thành công
        ra.addFlashAttribute("msg", "Cảm ơn bạn! Lời nhắn đã được gửi tới quản trị viên.");
        return "redirect:/contact";
    }
}
