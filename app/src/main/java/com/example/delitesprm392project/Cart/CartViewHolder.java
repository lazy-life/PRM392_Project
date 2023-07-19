package com.example.delitesprm392project.Cart;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.delitesprm392project.R;
import com.yonder.basketlayout.BasketLayoutView;

public class CartViewHolder extends ViewHolder {
    public ImageView itemImg;
    public TextView itemName;
    public TextView itemPrice;
    public ImageView btnDec;
    public TextView itemQuantity;
    public ImageView btnInc;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);

        itemImg = itemView.findViewById(R.id.cartItemImg);
        itemName = itemView.findViewById(R.id.cartItemName);
        itemPrice = itemView.findViewById(R.id.cartItemPrice);
        btnDec = itemView.findViewById(R.id.ivDec);
        itemQuantity = itemView.findViewById(R.id.cartItemQuantity);
        btnInc = itemView.findViewById(R.id.ivInc);
    }
}
