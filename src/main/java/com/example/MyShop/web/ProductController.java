package com.example.MyShop.web;

import com.example.MyShop.DAO.CartDAO;
import com.example.MyShop.DAO.ProductDAO;
import com.example.MyShop.model.Item;
import com.example.MyShop.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    private CartDAO cartDAO;
    private ProductDAO productDAO;
    Product replace;

    public ProductController(CartDAO cartDAO, ProductDAO productDAO) {
        this.cartDAO = cartDAO;
        this.productDAO = productDAO;
    }


    @GetMapping("/products/list")
    protected String doGet(Model model) {
        model.addAttribute("products", productDAO.all());
        return "products_list";
    }

    @GetMapping("/products/list/{name}")
    protected String showProduct(@PathVariable("name") String name, Model model) {
        model.addAttribute("product", productDAO.byName(name));
        model.addAttribute("addedItem", new Item(productDAO.byName(name), 0));
        return "product_details";
    }

    @GetMapping("/products/list/{name}/edit")
    protected String editProduct(@PathVariable("name") String name, Model model) {
        model.addAttribute("product", productDAO.byName(name));
        model.addAttribute("editProduct", new Product(productDAO.byName(name).getName(), productDAO.byName(name).getDescription(), productDAO.byName(name).getPrice(), productDAO.byName(name).getCategory()));
        return "product_edit";
    }

    @PostMapping("/products/list/{name}/update")
    public String confirmChange(@ModelAttribute Product editProduct, @PathVariable("name") String name, Model model) {
        if(checkNotEmpty(editProduct)) {
            model.addAttribute("editProduct", editProduct);
            model.addAttribute("product", productDAO.byName(name));
            replace = new Product(editProduct.getName(), editProduct.getDescription(), editProduct.getPrice(), editProduct.getCategory());
            return "update_success";
        } else
            return "redirect:sorry";
    }

    @PostMapping("/products/list/{name}/update/confirmed")
    public String inputChange(@PathVariable("name") String name, Model model) {
        System.out.println("zmieniono nazwę: " + productDAO.byName(name).getName() + " na: " + replace.getName());
        System.out.println("zmieniono opis: " + productDAO.byName(name).getDescription() + " na: " + replace.getDescription());
        System.out.println("zmieniono cenę: " + productDAO.byName(name).getPrice() + " na: " + replace.getPrice());
        System.out.println("zmieniono kategorię: " + productDAO.byName(name).getCategory() + " na: " + replace.getCategory());
        productDAO.removeProductByName(name);
        productDAO.addProduct(replace);
        model.addAttribute("products", productDAO.all());
        return "products_list";
    }

    @PostMapping("/products/list/{name}/addedToCart")
    public String addedToCart(@ModelAttribute Item addedItem, @PathVariable("name") String name, Model model) {
            addedItem = new Item(productDAO.byName(name), addedItem.getQuantity());
            cartDAO.addItem(addedItem);
            model.addAttribute("addedItem", addedItem);
            model.addAttribute("items", cartDAO.getAllItems());
            model.addAttribute("totalCost", cartDAO.getTotal());
            return "cart";
    }

    @GetMapping("/sorry")
    public String error() {
        return "errorMessage";
    }

    @GetMapping("/cart")
    protected String showCart(Model model) {
        model.addAttribute("items", cartDAO.getAllItems());
        model.addAttribute("totalCost", cartDAO.getTotal());
        return "cart";
    }

    private boolean checkNotEmpty(Product product) {
        return (product.getName()!=null && product.getName().length()>0)
                && (product.getDescription()!=null && product.getDescription().length()>0);
    }

    private boolean checkNotEmpty(Item item) {
        return (item.getProduct().getName()!=null && item.getProduct().getName().length()>0)
                && (item.getProduct().getDescription()!=null && item.getProduct().getDescription().length()>0)
                && (item.getQuantity()>0);
    }
}
