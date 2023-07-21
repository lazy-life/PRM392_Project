package com.example.delitesprm392project.Cart;

import com.example.delitesprm392project.model.Product;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
