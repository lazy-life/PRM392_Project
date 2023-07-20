package com.example.delitesprm392project.Cart;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.R;

public class CartOrderViewHolder extends RecyclerView.ViewHolder {
    public ImageView cartOrderImg;
    public TextView cartOrderName;
    public TextView cartOrderPrice;
    public TextView cartOrderQuantity;

    public CartOrderViewHolder(@NonNull View itemView) {
        super(itemView);
        cartOrderImg = itemView.findViewById(R.id.cartOrderImg);
        cartOrderName = itemView.findViewById(R.id.cartOrderName);
        cartOrderPrice = itemView.findViewById(R.id.cartOrderPrice);
        cartOrderQuantity = itemView.findViewById(R.id.cartOrderQuantity);
    }
}
