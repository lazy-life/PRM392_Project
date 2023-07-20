package com.example.delitesprm392project.RecyclerView.Product;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout productItem;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);

        productItem = itemView.findViewById(R.id.product_item);
    }
}
