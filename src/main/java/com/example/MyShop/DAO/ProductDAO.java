package com.example.MyShop.DAO;


import com.example.MyShop.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ProductDAO {

    private List<Product> products = new LinkedList<Product>(Arrays.asList(
            new Product("Jabłko", "bardzo słodkie, czerwone lub zielone", new BigDecimal("25.00"), "FRUIT"),
            new Product("Ziemniak", "Jabłko wśród warzyw", new BigDecimal("99.99"), "VEGETABLE"),
            new Product("Ogórek", "zielony, gruntowy", new BigDecimal("5.50"), "VEGETABLE")));

    public List<Product> all() {
        return products;
    }

    public void removeProductByName(String name) {
        for (Product product : products) {
            if (name.equals(product.getName())) {
                products.remove(product);
                break;
            }
        }
    }

    public void addProduct(Product product) {
                products.add(product);
            }

    public Product byName(String name) {
        for (Product product : products) {
            if (name.equals(product.getName())) {
                return product;
            }
        }
        return null;
    }

    public Product byCategory(String category) {
        for (Product product : products) {
            if (category.equals(product.getCategory())) {
                return product;
            }
        }
        return null;
    }
}
