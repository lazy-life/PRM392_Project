package com.example.delitesprm392project.Cart;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.delitesprm392project.R;
import com.example.delitesprm392project.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private List<CartItem> cartList;

    private Cart() {
        cartList = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addCartItem(CartItem cartItem) {
        cartList.add(cartItem);
    }

    public void removeCartItem(CartItem cartItem) {
        cartList.remove(cartItem);
    }

    public List<CartItem> getCartList() {
        return cartList;
    }
}