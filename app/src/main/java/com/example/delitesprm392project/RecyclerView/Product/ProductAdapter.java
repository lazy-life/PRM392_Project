package com.example.delitesprm392project.RecyclerView.Product;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.Cart.CartItem;
import com.example.delitesprm392project.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    // linh: fix cung list de hien thi cart
    public static List<CartItem> cartItemList = new ArrayList<>();
    // linh: fix cung list de hien thi cart

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
