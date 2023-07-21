package com.example.delitesprm392project.RecyclerView.ProductManager;

import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.R;

public class ProductManagerHolder extends RecyclerView.ViewHolder{
    public TextView productName;
    public TextView category, price;
    public ImageView image;
    public Button update_button;
    public Button delete_button;

    public ProductManagerHolder(@NonNull View itemView) {
        super(itemView);

        productName = itemView.findViewById(R.id.viewRecycleProductManagerName);
        category = itemView.findViewById(R.id.viewRecycleProductManagerCategory);
        price = itemView.findViewById(R.id.viewRecycleProductManagerPrice);
        update_button = itemView.findViewById(R.id.btnRecycleProductManagerUpdate);
        delete_button = itemView.findViewById(R.id.btnRecycleProductManagerDelete);
        image = itemView.findViewById(R.id.viewRecycleProductManagerImage);

    }
}
