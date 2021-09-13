package com.example.MyShop.web;

import com.example.MyShop.DAO.ProductDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProductController {

    private final ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping("/products/list")
    protected String doGet(Model model) {
        model.addAttribute("products", productDAO.all());
        return "products_list";
    }

    @GetMapping("/products/list/{name}")
    protected String doGet(@PathVariable("name") String name, Model model) {
        model.addAttribute("product", productDAO.byName(name));
        return "product_details";
    }

}
