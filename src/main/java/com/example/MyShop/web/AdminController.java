package com.example.MyShop.web;

import com.example.MyShop.DAO.CartDAO;
import com.example.MyShop.DAO.ProductDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private CartDAO cartDAO;
    private ProductDAO productDAO;

    public AdminController(CartDAO cartDAO, ProductDAO productDAO) {
        this.cartDAO = cartDAO;
        this.productDAO = productDAO;
    }


    @GetMapping("/admin")
    protected String adminPanel(Model model) {
        model.addAttribute("cart", cartDAO);
        model.addAttribute("products", productDAO.all());
        return "admin";
    }

    @GetMapping("/admin_list")
    protected String editProduct(Model model) {
        model.addAttribute("cart", cartDAO);
        model.addAttribute("products", productDAO.all());
        return "admin_list";
    }

    @GetMapping("/admin_add_product")
    protected String addProduct(Model model) {
        model.addAttribute("cart", cartDAO);
        model.addAttribute("products", productDAO.all());
        return "admin_add_product";
    }

    @GetMapping("/admin_categories")
    protected String editCategory(Model model) {
        model.addAttribute("cart", cartDAO);
        model.addAttribute("products", productDAO.all());
        return "admin_categories";
    }

    @GetMapping("/admin_add_category")
    protected String addCategory(Model model) {
        model.addAttribute("cart", cartDAO);
        model.addAttribute("products", productDAO.all());
        return "admin_add_category";
    }

}
