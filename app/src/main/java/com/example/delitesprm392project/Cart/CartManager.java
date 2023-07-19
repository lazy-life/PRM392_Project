package com.example.delitesprm392project.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private List<CartItem> cartList = new ArrayList<>();

    public void addItem(CartItem cartItem) {
        cartList.add(cartItem);
    }

    public void removeItem(CartItem cartItem) {
        cartList.remove(cartItem);
    }

    public List<CartItem> getCartItems() {
        return cartList;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (CartItem cartItem : cartList) {
            totalPrice += cartItem.getTotalPrice();
        }
        return totalPrice;
    }
}
