package com.example.MyShop.DAO;

import com.example.MyShop.model.Item;
import com.example.MyShop.model.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Repository
public class CartDAO {

    private List<Item> items = new LinkedList<>();

    public List<Item> getAllItems() {
        return items;
    }

    public Item getItemByProductName(String name) {
        if(items != null) {
            for (Item item : items) {
                if (name.equals(item.getProduct().getName())) {
                    return item;
                }
            }
        }
        return null;
    }

    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        if(items != null) {
            for (Item item : items) {
                sum = sum.add(item.getAmount());
            }
        }
        return sum;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

}
