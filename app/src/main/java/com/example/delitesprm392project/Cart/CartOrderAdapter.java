package com.example.delitesprm392project.Cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.R;
import com.example.delitesprm392project.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartOrderAdapter extends RecyclerView.Adapter<CartOrderViewHolder> {
    private List<CartItem> cartList;
    private Context mContext;

    public CartOrderAdapter(List<CartItem> cartList, Context mContext) {
        this.cartList = cartList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CartOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View cartView = inflater.inflate(R.layout.activity_cart_order, parent, false);
        CartOrderViewHolder viewHolder = new CartOrderViewHolder(cartView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartOrderViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CartItem cart = cartList.get(position);
        if (cart != null) {
            if (holder.cartOrderImg != null) {
                Picasso.get().load(cart.getProduct().getImagePath()).into(holder.cartOrderImg);
            }

            holder.cartOrderName.setText(cart.getProduct().getProductname());
            holder.cartOrderPrice.setText(String.valueOf(cart.getProduct().getProductprice()));
            holder.cartOrderQuantity.setText(String.valueOf(cart.getQuantity()));
        }
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
