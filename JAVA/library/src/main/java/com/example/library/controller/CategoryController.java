package com.example.library.controller;

import com.example.library.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import com.example.library.entity.Category;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    private CategoryService categoryService;
public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category-list")
    public String list(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);
        return "admin/category/category-list";
    }
    @GetMapping("/create")
    public String insert (Model model) {
        Category category = new Category();
        model.addAttribute("category",category);
        return "/admin/category/category-create";
    }

    @GetMapping("/edit-form")
    public String editForm(@RequestParam("id")int id, Model model) {
        Category category = categoryService.findById(id);
        model.addAttribute("category",category);
        return "admin/category/category-edit-form";
    }
    @PostMapping("/save")
    public String save(Category category) {
        categoryService.save(category);
        return "redirect:/admin/category/category-list";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        categoryService.deleteById(id);
        return "redirect:/admin/category/category-list";
    }
}
