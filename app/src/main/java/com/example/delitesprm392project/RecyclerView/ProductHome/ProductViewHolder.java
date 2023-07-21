package com.example.delitesprm392project.RecyclerView.ProductHome;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    TextView productName,productPrice;
    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.home_product_name);
        productPrice = itemView.findViewById(R.id.home_product_price);
    }
}
