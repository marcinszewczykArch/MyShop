package com.example.MyShop.web;

import com.example.MyShop.DAO.CartDAO;
import com.example.MyShop.DAO.CategoryDAO;
import com.example.MyShop.DAO.ProductDAO;
import org.springframework.stereotype.Controller;

@Controller
public class CartController {
    private CartDAO cartDAO;
    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public CartController(CartDAO cartDAO, ProductDAO productDAO, CategoryDAO categoryDAO) {
        this.cartDAO = cartDAO;
        this.productDAO = productDAO;
        this.categoryDAO = categoryDAO;
    }







}
