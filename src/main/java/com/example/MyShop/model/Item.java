package com.example.MyShop.model;

import java.math.BigDecimal;
import java.util.List;

public class Item {

        private final Product product;
        private final int quantity;

        public Item(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public BigDecimal getAmount() {
            return product.getPrice().multiply(BigDecimal.valueOf(quantity));
        }

    @Override
    public String toString() {
        return "Item{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
