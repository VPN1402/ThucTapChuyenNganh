package com.example.library.controller;
import com.example.library.entity.Category;
import com.example.library.entity.Product;
import com.example.library.service.CategoryService;
import com.example.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductController {


    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products-list")
    public String productList(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "admin/products/products-list";
    }
    @GetMapping("/create")
    public String insert (Model model) {
        Product product= new Product();
        List<Category> categories = categoryService.findAll();

        model.addAttribute("product", product);
        model.addAttribute("categories", categories); // Gửi danh sách này sang HTML
        return "/admin/products/product-create";
    }
    @GetMapping("/edit-form")
    public String editForm(@RequestParam("id") int id, Model model) {
        Product product = productService.findById(id);
        List<Category> categories = categoryService.findAll(); // Thêm dòng này

        model.addAttribute("product", product);
        model.addAttribute("categories", categories); // Gửi danh sách categories sang form edit
        return "admin/products/product-edit-form";
    }
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product,
                              @RequestParam("categoryId") int categoryId) {

        // 1. Tìm đối tượng Category từ DB bằng ID
        Category category = categoryService.findById(categoryId);

        // 2. Gán Category vào Product
        product.setCategory(category);

        // 3. Lưu Product
        productService.save(product);

        return "redirect:/admin/products/products-list";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id){
        productService.deleteById(id);
        return "redirect:/admin/products/products-list";
    }

}