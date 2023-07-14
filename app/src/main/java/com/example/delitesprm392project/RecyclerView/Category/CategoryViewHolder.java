package com.example.delitesprm392project.RecyclerView.Category;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delitesprm392project.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public Button button;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        button = itemView.findViewById(R.id.btnCategory);
        Log.d("viewholder",button.toString());
        button.setId(getAdapterPosition());

    }
}
