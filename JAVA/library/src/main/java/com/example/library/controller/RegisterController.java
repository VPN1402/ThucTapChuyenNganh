package com.example.library.controller;

import com.example.library.entity.UserDto;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @GetMapping("/dangky")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDto());
        return "home/dangky";
    }

    @PostMapping("/dangky")
    public String processRegistration(@ModelAttribute("user") UserDto userDto,
                                      Model model,
                                      RedirectAttributes redirectAttributes) { // Thêm tham số này

        String error = userService.registerNewUser(userDto);

        if (error != null) {
            model.addAttribute("registrationError", error);
            return "home/dangky";
        }

        // Nếu chạy đến đây là thành công, gán thông báo
        redirectAttributes.addFlashAttribute("successMessage", "Đăng ký thành công!");
        return "redirect:/login";
    }
}